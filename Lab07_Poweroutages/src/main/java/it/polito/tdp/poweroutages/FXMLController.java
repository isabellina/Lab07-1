/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Cboxnerc"
    private ChoiceBox<Nerc> Cboxnerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtY"
    private TextField txtY; // Value injected by FXMLLoader

    @FXML // fx:id="txtH"
    private TextField txtH; // Value injected by FXMLLoader

    @FXML // fx:id="buttonric"
    private Button buttonric; // Value injected by FXMLLoader
   
    @FXML
    private TextArea txtResult;

    @FXML
    void doRicorsione(ActionEvent event) {
    	try {
    		int anni = Integer.parseInt(txtY.getText());
    		int ore = Integer.parseInt(txtH.getText());
    	}
    	catch(NumberFormatException n) {
    		txtResult.appendText("Inserire un numero intero");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Cboxnerc != null : "fx:id=\"Cboxnerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtY != null : "fx:id=\"txtY\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtH != null : "fx:id=\"txtH\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonric != null : "fx:id=\"buttonric\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	ObservableList<Nerc> nerc = FXCollections.observableList(model.getNercList());
    	
    	Cboxnerc.setItems(nerc);
    	Cboxnerc.setValue(nerc.get(0));
    	
    }
}

