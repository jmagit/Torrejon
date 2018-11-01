package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.model.EmpleadoModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmpleadosFormModelView implements Initializable {
	@FXML
	protected Button btnAcept;
	@FXML
	protected Button btnCancel;
	@FXML
	protected TextField txtIdEmpleado;
	@FXML
	protected TextField txtNombre;
	@FXML
	protected TextField txtApellidos;
	@FXML
	protected ComboBox<String> txtDelegacion;
	@FXML
	protected CheckBox chkConflictivo;

	private EmpleadoModel model = new EmpleadoModel();
	
	public EmpleadoModel getModel() {
		return model;
	}
	public void setModel(EmpleadoModel model) {
		this.model.copy(model);
	}
	public void setModel(Empleado item) {
		this.model.copy(item);
	}
	public void setCommand(EventHandler<ActionEvent> acept, EventHandler<ActionEvent> cancel) {
		if(btnAcept != null) btnAcept.setOnAction(acept);
		if(btnCancel != null) btnCancel.setOnAction(cancel);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtIdEmpleado.textProperty().bindBidirectional(model.getIdEmpleadoProperty());
		txtNombre.textProperty().bindBidirectional(model.getNombreProperty());
		txtApellidos.textProperty().bindBidirectional(model.getApellidosProperty());
		txtDelegacion.valueProperty().bindBidirectional(model.getDelegacionProperty());
		txtDelegacion.setItems(model.getDelegaciones());
		chkConflictivo.selectedProperty().bindBidirectional(model.getConflictivoProperty());
	}



}
