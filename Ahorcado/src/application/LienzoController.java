package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;

public class LienzoController implements Initializable {
	@FXML
	Button btnFalla, btnAcierta;
	@FXML
	private Canvas capa1;
	@FXML
	private Canvas capa2;
	
	private GraphicsContext gc1, gc2;
	
	private int fallos = 0;
	
	private void pintaHorca() {
		gc1.setLineWidth(3);
		gc1.strokeLine(100, 40, 250, 40);
		gc1.strokeLine(100, 40, 100, 340);
		gc1.strokeLine(70, 340, 130, 340);
		gc1.strokeLine(100, 90, 150, 40);
		gc1.strokeLine(250, 40, 250, 90);
	}
	
	private void pintaCabeza() {
		gc2.setLineWidth(1);
		gc2.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.REFLECT,
	               new Stop(0.0, Color.RED), new Stop(1.0, Color.YELLOW)));
		gc2.setStroke(Color.RED);

		gc2.fillOval(230, 79, 40, 40);
		gc2.strokeOval(230, 79, 40, 40);
		gc2.setLineWidth(2);
	}
	
	private void pintaTronco() {
		gc2.strokeLine(250, 120, 250, 220);
	}
	
	private void pintaBrazoIzq() {
		gc2.strokeLine(220, 197, 250, 132);
	}
	
	private void pintaBrazoDer() {
		gc2.strokeLine(250, 132, 280, 197);
	}
	
	private void pintaPiernaIzq() {
		gc2.strokeLine(250, 220, 210, 295);
	}
	
	private void pintaPiernaDer() {
		gc2.strokeLine(250, 220, 290, 295);
	}
	
	private void limpia() {
		gc2.clearRect(0, 0, capa2.getWidth(), capa2.getHeight());
	}
	

	public void onInit(Event ev) {
		fallos = 0;
		btnAcierta.setVisible(true);
		btnFalla.setVisible(true);
		limpia();
	}
	public void onFalla(Event ev) {
		switch (++fallos) {
		case 6: 
			pintaPiernaDer();
			btnAcierta.setVisible(false);
			btnFalla.setVisible(false);
		case 5: pintaPiernaIzq();
		case 4: pintaBrazoDer();
		case 3: pintaBrazoIzq();
		case 2: pintaTronco();
		case 1: pintaCabeza();
		}
	}
	public void onAcierta(Event ev) {
		if(fallos == 0) return;
		limpia();
		switch (--fallos) {
		case 6: 
			pintaPiernaDer();
			btnFalla.setVisible(false);
		case 5: pintaPiernaIzq();
		case 4: pintaBrazoDer();
		case 3: pintaBrazoIzq();
		case 2: pintaTronco();
		case 1: pintaCabeza();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc1 = capa1.getGraphicsContext2D();
		gc2 = capa2.getGraphicsContext2D();
		
		pintaHorca();
		onInit(null);
	}

}
