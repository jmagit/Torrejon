package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PrincipalController implements Initializable {
	@FXML
	private Text cotilla;
	@FXML
	private Shape cabeza;
	@FXML
	private ColorPicker cb;
	@FXML
	private SVGPath svg;
	@FXML
	private ImageView img;
	@FXML
	private WebView navegador;
	@FXML
	Path path;	

	public void onClickCabeza(Event ev) {
		cotilla.setText("Cabeza");
		cabeza.setFill(Color.ORANGE);
		cabeza.setVisible(false);
	}

	public void onClickMano(Event ev) {
		cotilla.setText("Mano");
		cabeza.setFill(cb.getValue());
	}

	public void onCamiseta(Event ev) {
		cotilla.setText("Camiseta");
		svg.setFill(cb.getValue());
	       Image image = new Image("https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/pencil.svg");
	       img.setImage(image);
		

	}
	public void onKK(Event ev) {
		MoveTo moveTo = new MoveTo();
		moveTo.setX(10.0f);
		moveTo.setY(100.0f);
		HLineTo hLineTo = new HLineTo();
		hLineTo.setX(70.0f);
		QuadCurveTo quadCurveTo = new QuadCurveTo();
		quadCurveTo.setX(120.0f);
		quadCurveTo.setY(60.0f);
		quadCurveTo.setControlX(100.0f);
		quadCurveTo.setControlY(0.0f);
		LineTo lineTo = new LineTo();
		lineTo.setX(175.0f);
		lineTo.setY(55.0f);
		ArcTo arcTo = new ArcTo();
		arcTo.setX(50.0f);
		arcTo.setY(50.0f);
		arcTo.setRadiusX(50.0f);
		arcTo.setRadiusY(50.0f);
		path.getElements().addAll(moveTo, hLineTo, quadCurveTo, lineTo, arcTo);
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
       Image image = new Image("https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/pencil.svg");
       img.setImage(image);
		WebEngine webEngine = navegador.getEngine();
		webEngine.load("https://dev.w3.org/SVG/tools/svgweb/samples/svg-files/pencil.svg");
	}
	
}
