package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PrincipalController implements Initializable {
	@FXML
	private ColorPicker cbColor;
	@FXML
	private CheckBox ckCapa1;
	@FXML
	private CheckBox ckCapa2;
	@FXML
	private Canvas capa1;
	@FXML
	private Canvas capa2;
	@FXML
	private ChoiceBox<String> cbFrente;
	@FXML
	private Slider slider;
	
	private GraphicsContext gc1, gc2;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gc1 = capa1.getGraphicsContext2D();
		gc2 = capa2.getGraphicsContext2D();
		cbFrente.setItems(FXCollections.observableArrayList("Capa1", "Capa2"));
		cbFrente.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number old, Number act) -> {
					if(act.doubleValue() == 0) {
						capa1.toFront();
					} else {
						capa2.toFront();
					}
				}
				);
		
		drawShapes(gc1);
		cbColor.setValue(Color.GREEN);
		drawShapes(gc2);
		gc2.applyEffect(new DropShadow(20, 20, 20, Color.GRAY));
		ckCapa1.setSelected(true);
		ckCapa2.setSelected(true);
		capa1.visibleProperty().bind(ckCapa1.selectedProperty());
		capa2.visibleProperty().bind(ckCapa2.selectedProperty());
		
		capa1.addEventHandler(
				MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				gc1.setFill(Color.AQUA);
				gc1.fillOval(t.getX()-15, t.getY()-15, 30, 30);
			}
		});
		capa2.addEventHandler(
				MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				gc2.setFill(Color.ORANGE);
				gc2.fillOval(t.getX()-15, t.getY()-15, 30, 30);
			}
		});
		
		capa2.setTranslateX(500);
		slider.valueProperty().addListener((
	            ObservableValue<? extends Number> ov, 
	            Number old_val, Number new_val) -> {
	            	capa2.setTranslateX(new_val.doubleValue());
	        });
	}
	
	public void onRepinta(ActionEvent ev) {
		drawShapes(gc1);
	}
	
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(cbColor.getValue());
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }

	
}
