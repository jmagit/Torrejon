package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	static private Stage principalStage = null;
	@Override
	public void start(Stage primaryStage) {
		try {
			principalStage = primaryStage;
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Principal.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("1683_Lightbulb_256x256.png"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Aplicación Múltiple");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getPrincipalStage () {
		return principalStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
