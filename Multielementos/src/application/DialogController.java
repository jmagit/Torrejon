package application;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DialogController {
	private Stage escenario;
	
	
	public Stage getEscenario() {
		return escenario;
	}


	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}


	public void onAceptar(ActionEvent ev) {
		if (this.escenario == null) return;
		// ...
		this.escenario.close();
	}
	public void onCancelar(ActionEvent ev) {
		if (this.escenario == null) return;
		this.escenario.close();
	}
	
}
