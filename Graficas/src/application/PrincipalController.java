package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PrincipalController implements Initializable {
	@FXML
	private BorderPane root;
	@FXML
	private Label valX;
	@FXML
	private Label valY;
	@FXML
	private Label valData;
	
	
	public void onPieChart(ActionEvent ev) {
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.RIGHT);
        
        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        caption.setTranslateX(e.getX());
                        caption.setTranslateY(e.getY());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                        valX.setText(Double.toString(e.getX()));
                        valY.setText(Double.toString(e.getY()));
                        valData.setText(String.valueOf(data.getPieValue()) + "%");
                     }
                });
        }

        root.setCenter(new StackPane(chart, caption));
        Random rnd = new Random();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
            (ActionEvent actionEvent) -> {            	
            	chart.getData().stream().forEach((data) -> {
            		int nuevo = rnd.nextInt();
            		if(nuevo != data.getPieValue())
            			data.setPieValue(nuevo);
                });
            }
        ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();

	}

	private String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun",
			"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	private XYChart.Series SerieConCategoria(String titulo) {
		Random rnd = new Random();
        XYChart.Series series = new XYChart.Series();
        series.setName(titulo);
        for(String mes: meses)
        	series.getData().add(new XYChart.Data(mes, rnd.nextInt() % 100 + 1));
        return series;
	}
	private XYChart.Series SerieConNumeros(String titulo) {
		Random rnd = new Random();
        XYChart.Series series = new XYChart.Series();
        series.setName(titulo);
        for(int i=1; i <= 12; i++)
        	series.getData().add(new XYChart.Data(i, rnd.nextInt() % 100 + 1));
        return series;
	}
	private void anima(XYChart<String, Number> chart) {
        Random rnd = new Random();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
            (ActionEvent actionEvent) -> {            	
            	chart.getData().stream().forEach((series) -> {
                    series.getData().stream().forEach((data) -> {
                		int nuevo = rnd.nextInt(100);
                        data.setYValue(nuevo);
                    });
                });
            }
        ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();

	}
	private void anima2(XYChart<Number, Number> chart) {
        Random rnd = new Random();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
            (ActionEvent actionEvent) -> {            	
            	chart.getData().stream().forEach((series) -> {
                    series.getData().stream().forEach((data) -> {
                		int nuevo = rnd.nextInt(100);
                        data.setYValue(nuevo);
                    });
                });
            }
        ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();

	}
	public void onLineChart(ActionEvent ev) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Meses");       
       
        final LineChart<String,Number> chart = 
                new LineChart<String,Number>(xAxis,yAxis);
        chart.setTitle("Stock Monitoring, 2010");
        chart.setLegendSide(Side.RIGHT);
        chart.getData().add(SerieConCategoria("Manzanas"));
        chart.getData().add(SerieConCategoria("Peras"));
        chart.getData().add(SerieConCategoria("Uvas"));
        root.setCenter(chart);
        anima(chart);
	}

	public void onLineNumberChart(ActionEvent ev) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Meses");       
       
        final LineChart<Number,Number> chart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        chart.setTitle("Stock Monitoring, 2010");
        chart.setLegendSide(Side.RIGHT);
        chart.getData().add(SerieConNumeros("Manzanas"));
        chart.getData().add(SerieConNumeros("Peras"));
        chart.getData().add(SerieConNumeros("Uvas"));
        
        chart.getData().stream().forEach((series) -> {
            series.getData().stream().forEach((data) -> {
            	data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        valX.setText(Double.toString(e.getX()));
                        valY.setText(Double.toString(e.getY()));
                        valData.setText(String.valueOf(data.getYValue()) + "%");
                     }
            	});
            });
           });
            
        root.setCenter(chart);
        anima2(chart);
	}

	public void onAreaChartChart(ActionEvent ev) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis(-100, 100, 20);
        xAxis.setLabel("Meses");       
       
        final AreaChart<Number,Number> chart = 
                new AreaChart<Number,Number>(xAxis,yAxis);
        chart.setTitle("Stock Monitoring, 2010");
        chart.setLegendSide(Side.RIGHT);
        chart.getData().add(SerieConNumeros("Manzanas"));
        chart.getData().add(SerieConNumeros("Peras"));
        chart.getData().add(SerieConNumeros("Uvas"));
        
        chart.getData().stream().forEach((series) -> {
            series.getData().stream().forEach((data) -> {
            	data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        valX.setText(Double.toString(e.getX()));
                        valY.setText(Double.toString(e.getY()));
                        valData.setText(String.valueOf(data.getYValue()) + "%");
                     }
            	});
            });
           });
            
        root.setCenter(chart);
        anima2(chart);
	}

	public void onBarChart(ActionEvent ev) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Meses");       
       
        final BarChart<String,Number> chart = 
                new BarChart<String,Number>(xAxis,yAxis);
        chart.setTitle("Stock Monitoring, 2010");
        chart.setLegendSide(Side.RIGHT);
        chart.getData().add(SerieConCategoria("Manzanas"));
        chart.getData().add(SerieConCategoria("Peras"));
        chart.getData().add(SerieConCategoria("Uvas"));
        root.setCenter(chart);
        anima(chart);
	}

	public void onStackedAreaChart(ActionEvent ev) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis(-100, 100, 20);
        xAxis.setLabel("Meses");       
       
        final StackedAreaChart<Number,Number> chart = 
                new StackedAreaChart<Number,Number>(xAxis,yAxis);
        chart.setTitle("Stock Monitoring, 2010");
        chart.setLegendSide(Side.RIGHT);
        chart.getData().add(SerieConNumeros("Manzanas"));
        chart.getData().add(SerieConNumeros("Peras"));
        chart.getData().add(SerieConNumeros("Uvas"));
        
        chart.getData().stream().forEach((series) -> {
            series.getData().stream().forEach((data) -> {
            	data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        valX.setText(Double.toString(e.getX()));
                        valY.setText(Double.toString(e.getY()));
                        valData.setText(String.valueOf(data.getYValue()) + "%");
                     }
            	});
            });
           });
            
        root.setCenter(chart);
        anima2(chart);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		onBarChart(null);
		
	}
}
