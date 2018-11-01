package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PrincipalController {
	@FXML
	BorderPane root;
	
	public void onFiguras(Event ev) throws IOException {
		root.setCenter(FXMLLoader.load(getClass().getResource("Figuras.fxml")));
	}
	public void onLienzo(Event ev) throws IOException {
		root.setCenter(FXMLLoader.load(getClass().getResource("Lienzo.fxml")));
	}

}
