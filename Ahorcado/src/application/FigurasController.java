package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class FigurasController implements Initializable {
	@FXML
	AnchorPane root;
	@FXML
	Button btnFalla, btnAcierta;
	@FXML
	private Shape cabeza, tronco, brazoIzq, brazoDer, piernaIzq, piernaDer;

	private int fallos = 0;

	private void verParte(Shape nodo) {
		// nodo.setVisible(true);
		// nodo.setOpacity(1);
		FadeTransition ft = new FadeTransition(Duration.millis(3000), nodo);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();

	}

	private void ocultaParte(Shape nodo) {
		// nodo.setVisible(false);
		// nodo.setOpacity(0.10);
		FadeTransition ft = new FadeTransition(Duration.millis(3000), nodo);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	public void onInit(Event ev) {
		fallos = 0;
		btnAcierta.setVisible(true);
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), btnAcierta);
		scaleTransition.setFromX(0.10);
		scaleTransition.setFromY(0.10);
		scaleTransition.setToX(1);
		scaleTransition.setToY(1);
		scaleTransition.setCycleCount(1);
		scaleTransition.setAutoReverse(false);
		scaleTransition.play();
		btnFalla.setVisible(true);
		ScaleTransition scaleTransitionFalla = new ScaleTransition(Duration.millis(2000), btnFalla);
		scaleTransitionFalla.setFromX(0.10);
		scaleTransitionFalla.setFromY(0.10);
		scaleTransitionFalla.setToX(1);
		scaleTransitionFalla.setToY(1);
		scaleTransitionFalla.setCycleCount(1);
		scaleTransitionFalla.setAutoReverse(false);
		scaleTransitionFalla.play();
		for (Node nodo : root.getChildren()) {
			if (nodo instanceof Shape && ((Shape) nodo).getStrokeWidth() < 3) {
				ocultaParte((Shape) nodo);
			}
		}
	}

	public void onAcierta(Event ev) {
		if (fallos == 0)
			return;

		switch (fallos--) {
		case 6:
			ocultaParte(piernaDer);
			break;
		case 5:
			ocultaParte(piernaIzq);
			break;
		case 4:
			ocultaParte(brazoDer);
			break;
		case 3:
			ocultaParte(brazoIzq);
			break;
		case 2:
			ocultaParte(tronco);
			break;
		case 1:
			ocultaParte(cabeza);
			break;
		}
	}

	public void onFalla(Event ev) {
		switch (++fallos) {
		case 6:
			verParte(piernaDer);
			btnAcierta.setVisible(false);
			btnFalla.setVisible(false);
		case 5:
			verParte(piernaIzq);
		case 4:
			verParte(brazoDer);
		case 3:
			verParte(brazoIzq);
		case 2:
			verParte(tronco);
		case 1:
			verParte(cabeza);
		}
	}

	public void onParteClick(Event ev) {
		if (ev.getSource() instanceof Shape)
			ocultaParte((Shape) ev.getSource());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		onInit(null);
	}

}
