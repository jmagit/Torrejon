package calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculadoraController {
    private Calculadora calculadora = new Calculadora();
    private boolean limpia = true;
    @FXML
    private Label txtPantalla;

    public void DigitoPulsado(ActionEvent e) throws CalculadoraException {
        String d = (String) ((Button) e.getSource()).getText();
        String s = (String) txtPantalla.getText();
        if(d == null || d.length() != 1 || "0".compareTo(d) == 1 || "9".compareTo(d) == -1) 
        	throw new CalculadoraException("No es un digito.");
        if(s.equals("0") || limpia) {
            s = "";
            limpia = false;
        }
        txtPantalla.setText(s + d);
    }

    public void OperacionPulsada(ActionEvent e) {
        try {
            char op = ((Button) e.getSource()).getText().charAt(0);
            ponResultadoFormateado(calculadora.calcula(txtPantalla.getText(), op));
            limpia = true;
        } catch(CalculadoraException ex) {
            muestraExcepcion(ex);
        }
    }

    public void btnIni(ActionEvent e) {
        calculadora.inicializa();
        txtPantalla.setText("0");
    }

    public void btnDecimal(ActionEvent e) {
        String s = (String) txtPantalla.getText();
        if(limpia) {
            txtPantalla.setText("0,");
            limpia = false;
        } else if(s.indexOf(',') == -1) {
            txtPantalla.setText(s + ",");
        }
    }

    public void btnSigno(ActionEvent e) {
        ponResultadoFormateado(-1 * dameOperandoDePantalla());
    }

    public void btnBorra(ActionEvent e) {
        String s = (String) txtPantalla.getText();
        if(limpia || s.length() == 1 || s.equals("0,")) {
            txtPantalla.setText("0");
            limpia = false;
        } else {
            txtPantalla.setText(s.substring(0, s.length() - 1));
        }
    }
    private void ponResultadoFormateado(double rslt) {
        String s = Double.toString(rslt);
        if(s.endsWith(".0")) {
        	txtPantalla.setText(s.replace(".0", ""));
        } else {
        	txtPantalla.setText(s.replace(".", ","));
        }
    }

    private double dameOperandoDePantalla() {
        String operando2 = txtPantalla.getText();
        if(operando2.endsWith(",")) {
            operando2 += "0";
        }
        return Double.parseDouble(operando2.replace(',', '.'));
    }

    private void muestraExcepcion(CalculadoraException ex) {
    	Alert dlg = new Alert(AlertType.ERROR, ex.getMessage());
    	dlg.showAndWait();
    }

}
