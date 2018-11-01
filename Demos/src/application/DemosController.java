package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.beans.property.SimpleStringProperty;

public class DemosController implements Initializable {
	@FXML
	private Label lblResult;
	
	@FXML
	private Button btnCommand;

	@FXML
	private Button btnSaluda;

	@FXML
	private TextField txtName;

	StringProperty name = new SimpleStringProperty("Mundo");
	
	EventHandler<ActionEvent> saluda = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			lblResult.setText("Hola " + txtName.getText());
			btnSaluda.removeEventHandler(ActionEvent.ACTION, saluda);
			btnSaluda.addEventHandler(ActionEvent.ACTION, despide);
			btnSaluda.setText("Despide");
		}
	};

	EventHandler<ActionEvent> despide = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			lblResult.setText("Adios " + txtName.getText());
			btnSaluda.removeEventHandler(ActionEvent.ACTION, despide);
			btnSaluda.addEventHandler(ActionEvent.ACTION, saluda);
			btnSaluda.setText("Saluda");
		}
	};
	
	public void cambia(ActionEvent ev) {
		//name = new SimpleStringProperty("kk");
		name.set("kk");
		if ("Saluda".equals(btnSaluda.getText())) {
			btnSaluda.removeEventHandler(ActionEvent.ACTION, this.saluda);
			btnSaluda.addEventHandler(ActionEvent.ACTION, this.despide);
			btnSaluda.setText("Despide");
		} else {
			btnSaluda.removeEventHandler(ActionEvent.ACTION, this.despide);
			btnSaluda.addEventHandler(ActionEvent.ACTION, this.saluda);
			btnSaluda.setText("Saluda");
		}
	}

	public void saluda(ActionEvent ev) {
		this.lblResult.setText("Hello " + txtName.getText());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//txtName.setText("World");
		txtName.textProperty().bindBidirectional(name);
		lblResult.textProperty().bind(name);
		btnSaluda.addEventHandler(ActionEvent.ACTION,this.saluda);
		btnSaluda.setText("Saluda");
	}
	
}
