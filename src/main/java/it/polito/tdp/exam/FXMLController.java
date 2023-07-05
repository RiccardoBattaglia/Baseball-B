/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.exam;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.exam.model.Arco;
import it.polito.tdp.exam.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnDettagli"
    private Button btnDettagli; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbAnno"
    private ComboBox<Integer> cmbAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSquadra"
    private ComboBox<String> cmbSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtTifosi"
    private TextField txtTifosi; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	String nome=cmbSquadra.getValue();
    	
//    	controlli sull'input
    	if (nome==null) {
    		this.txtResult.setText("Inersire una squadra.\n");
    		return;
    	}
    	
//    	creazione grafo
    	this.model.creaGrafo(nome);
    	
    	
//    	stampa grafo
    	this.txtResult.setText("Grafo creato.\n");
    	this.txtResult.appendText("Ci sono " + this.model.nVertici() + " vertici\n");
    	this.txtResult.appendText("Ci sono " + this.model.nArchi() + " archi\n\n");
    	
    	btnDettagli.setDisable(false);
    	cmbAnno.setDisable(false);

    }

    @FXML
    void handleDettagli(ActionEvent event) {
    	
    	this.txtResult.clear();
//    	stampa grafo
    	this.txtResult.setText("Grafo creato.\n");
    	this.txtResult.appendText("Ci sono " + this.model.nVertici() + " vertici\n");
    	this.txtResult.appendText("Ci sono " + this.model.nArchi() + " archi\n\n");
    	  	
//    	controlli sull'input
    	if (cmbAnno.getValue()==null) {
    		this.txtResult.appendText("Inerire un anno.\n");
    		return;
    	}
    	
    		int anno=cmbAnno.getValue();
    		
    		if(!this.model.getVertici().contains(anno)) {
    			this.txtResult.appendText("Non ci sono dati per questo anno.\n");
    			return;
    		}
    		
    	
    	
    		List<Arco> dettagli = new ArrayList<>();
    		dettagli.addAll(this.model.getDettagli(cmbSquadra.getValue(), anno));
    	
    		for(Arco d : dettagli) {
    			this.txtResult.appendText(anno+"<-> anno:"+d.getAnno()+"; peso:"+d.getPeso()+"\n");
    		} 		
    	

    }

    @FXML
    void handleSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDettagli != null : "fx:id=\"btnDettagli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSquadra != null : "fx:id=\"cmbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTifosi != null : "fx:id=\"txtTifosi\" was not injected: check your FXML file 'Scene.fxml'.";
        
       

    }

    public void setModel(Model model) {
        this.model = model;
        
        cmbSquadra.getItems().addAll(this.model.readAllNameTeams());
        cmbAnno.getItems().addAll(this.model.readAllYears());
        
        btnDettagli.setDisable(true);
        cmbAnno.setDisable(true);
        
    }

}
