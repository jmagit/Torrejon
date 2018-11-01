package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.dal.Empleado;
import application.dal.EmpleadosDAO;
import application.model.EmpleadoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class EmpleadosMntModelView implements Initializable {
	@FXML
	protected StackPane root;

	protected Parent listPane = null, formPane = null;
	protected EmpleadosLstModelView listController;
	protected EmpleadosFormModelView formController;
	protected EventHandler<ActionEvent> addHandler;
	protected EventHandler<ActionEvent> modifyHandler;
	protected EventHandler<ActionEvent> removeHandler;
	protected EventHandler<ActionEvent> aceptAddHandler;
	protected EventHandler<ActionEvent> aceptModifyHandler;
	protected EventHandler<ActionEvent> cancelHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EmpleadosLstView.fxml"));
		try {
			listPane = (Parent) loader.load();
			listController = (EmpleadosLstModelView) loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EmpleadosFormView.fxml"));
		try {
			formPane = (Parent) loader.load();
			formController = (EmpleadosFormModelView) loader.getController();
			formPane.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		root.getChildren().addAll(listPane, formPane);

		aceptAddHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					add(formController.getModel());
					load();
					cancelHandler.handle(null);
				} catch (Exception  e) {
					e.printStackTrace();
				}
			}
		};
		aceptModifyHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					modify(formController.getModel());
					load();
					cancelHandler.handle(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		cancelHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				verList();
			}
		};

		addHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				formController.setModel(new Empleado());
				formController.setCommand(aceptAddHandler, cancelHandler);
				verForm();
			}
		};
		modifyHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (listController.getElemento() == null)
						return;
					formController.setModel(load(listController.getElemento().getIdEmpleado()));
					formController.setCommand(aceptModifyHandler, cancelHandler);
					verForm();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		removeHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (listController.getElemento() != null)
					try {
						remove(listController.getElemento().getIdEmpleado());
						load();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		};

		listController.setCommand(addHandler, modifyHandler, removeHandler);
		load();
	}

	protected void load() {
		EmpleadosDAO srv = new EmpleadosDAO();
		ObservableList<EmpleadoModel> lst = FXCollections.observableArrayList();
		try {
			for (Empleado emp : srv.getAll()) {
				lst.add(new EmpleadoModel(emp));
			}
			listController.setListado(lst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Empleado load(int id) throws Exception {
		EmpleadosDAO srv = new EmpleadosDAO();
		return srv.get(id);
	}

	public void add(EmpleadoModel item) throws Exception {
		EmpleadosDAO srv = new EmpleadosDAO();
		if (item == null)
			throw new Exception("Datos invalidos");
		srv.add(item.getEmpleado());
	}

	public void modify(EmpleadoModel item) throws Exception {
		EmpleadosDAO srv = new EmpleadosDAO();
		if (item == null)
			throw new Exception("Datos invalidos");
		srv.edit(item.getEmpleado());
	}

	public void remove(int id) throws Exception {
		EmpleadosDAO srv = new EmpleadosDAO();
		srv.delete(id);
	}

	public void verList() {
		listPane.setVisible(true);
		formPane.setVisible(false);
	}

	public void verForm() {
		listPane.setVisible(false);
		formPane.setVisible(true);
	}

}
