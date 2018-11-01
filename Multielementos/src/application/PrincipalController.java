package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController {
	@FXML
	BorderPane panel;
	@FXML
	Label lbMensaje;
	@FXML
	ProgressBar bar;

	public void onCalculadora(ActionEvent ev) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Calculadora.fxml"));
			Parent root = (Parent) loader.load();
			CalculadoraController controller = (CalculadoraController) loader.getController();
			panel.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onPaneles(ActionEvent ev) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Paneles.fxml"));
			Parent root = (Parent) loader.load();
			PanelesController controller = (PanelesController) loader.getController();
			panel.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDemos(ActionEvent ev) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Demos.fxml"));
			Parent root = (Parent) loader.load();
			DemosController controller = (DemosController) loader.getController();
			controller.setAnfitrion(this);
			panel.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onEditor(ActionEvent ev) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Editor.fxml"));
			Parent root = (Parent) loader.load();
			EditorController controller = (EditorController) loader.getController();
			panel.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void Limpia() {
		panel.setCenter(null);
	}

	public void onLimpia(ActionEvent ev) {
		Limpia();
	}

	public void onClose(ActionEvent ev) {
		System.exit(0);
	}

	public void onCambiaScene(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Ventana.fxml"));
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.getPrincipalStage().setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onOpenNoModalSinPadre(ActionEvent ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Ventana.fxml"));
		Parent ventana = (Parent) loader.load();
		Scene scene = new Scene(ventana, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Modeless sin padre");
		dialogStage.initModality(Modality.NONE);
		// dialogStage.initOwner(Main.getPrincipalStage());
		dialogStage.setScene(scene);
		dialogStage.show();
	}

	public void onOpenNoModalConPadre(ActionEvent ev) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Ventana.fxml"));
		Parent ventana = (Parent) loader.load();
		Scene scene = new Scene(ventana, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Modeless con padre");
		dialogStage.initModality(Modality.NONE);
		dialogStage.initOwner(Main.getPrincipalStage());
		dialogStage.setScene(scene);
		dialogStage.show();
		// En okHandle o cancelHandle: dialogStage.close();
	}

	public void openDialog(ActionEvent e) throws IOException {
		Stage dialogStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("dialog.fxml"));
		Parent ventana = (Parent) loader.load();
		((DialogController) loader.getController()).setEscenario(dialogStage);
		Scene scene = new Scene(ventana, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogStage.setTitle("Child Window");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(Main.getPrincipalStage());
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
		// En okHandle o cancelHandle: dialogStage.close();
	}

	public void onPintaGrafica(ActionEvent ev) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Grapefruit", 13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10),
				new PieChart.Data("Pears", 22), new PieChart.Data("Apples", 30));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Imported Fruits");
		panel.setCenter(chart);
	}
	public void onNavega(ActionEvent ev) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("https://docs.oracle.com/javase/8/javase-clienttechnologies.htm");

		panel.setCenter(browser);
	}
	
	public void onListado1(ActionEvent ev) {
		WebView browser = new WebView();
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body><h1>Listado</h1><table border=\"1\">");
		sb.append("<tr><th>Código</th><th>Nombre</th><th>Apellidos</th></tr>");
		for(int i = 1; i++ < 100;) {
		sb.append(String.format("<tr><td>%d</td><td>Nombre %d</td><td>Apellidos %d</td></tr>", i, i, i));
		}
		sb.append("</table></body></html>");
		browser.getEngine().loadContent(sb.toString());

		panel.setCenter(browser);
	}
	public void onListado(ActionEvent ev) {
		WebView browser = new WebView();
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body style='background-color: rgb(165,216,254)'><h1>Listado</h1>");
		sb.append("<ul>");
		for(int i = 1; i++ < 100;) {
		sb.append(String.format("<li>Nombre%d Apellidos %d (%d)</li>", i, i, i));
		}
		sb.append("</ul></body></html>");
		browser.getEngine().loadContent(sb.toString());

		panel.setCenter(browser);
	}
	
	public void onVerVideo(ActionEvent e) {
		Media media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		MediaView player = new MediaView(mediaPlayer);
		panel.setCenter(player);
		//mediaPlayer.play();
	}
	public void onGoogle(ActionEvent e) throws Exception {
		startService("https://google.es", 0);
	}
	public void onOracle(ActionEvent e) throws Exception {
		startService("https://docs.oracle.com/javase/8/javafx/visual-effects-tutorial/effect-types.htm", 2000);
	}
	public void startService(String URL, int delay) throws Exception {
	    FirstLineService service = new FirstLineService(URL, delay);
	    //lbMensaje.textProperty().bind(service.messageProperty());
	    lbMensaje.textProperty().bind(service.urlProperty());
	    service.setUrl(URL);
	    service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	        @Override
	        public void handle(WorkerStateEvent t) {
	        	panel.setCenter(new TextFlow(new Text(t.getSource().getValue().toString())));
	        }
	    });
	    service.start();
	}
	public void onHilo(ActionEvent e) throws Exception {
		final Group group = new Group();

	     Task<Void> task = new Task<Void>() {
	         @Override protected Void call() throws Exception {
	             for (int i=0; i<100; i++) {
	                 if (isCancelled()) break;
	                 final Rectangle r = new Rectangle(10, 10);
	                 r.setX(10 * i);
	                 Platform.runLater(new Runnable() {
	                     @Override public void run() { group.getChildren().add(r); }
	                 });
	                 updateProgress(i, 100);
	                 Thread.sleep(20);
	             }
	             return null;
	         }
	     };
	     bar.progressProperty().bind(task.progressProperty());
	     (new Thread(task)).start();
	     panel.setCenter(group);

	}


}
