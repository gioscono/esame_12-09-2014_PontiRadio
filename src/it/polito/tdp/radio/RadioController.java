package it.polito.tdp.radio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.radio.bean.Citta;
import it.polito.tdp.radio.bean.Model;
import it.polito.tdp.radio.bean.Ponte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class RadioController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextArea txtResult;
    @FXML
    private ComboBox<Citta> boxCittaA;

    @FXML
    private ComboBox<Citta> boxCittaB;

    @FXML
    private ComboBox<Citta> boxCittaC;

    @FXML
    void doCercaPonti(ActionEvent event) {
    	Citta a = boxCittaA.getValue();
    	if(a == null){
    		txtResult.appendText("Errore: selezionare la prima citta.\n");
    		return;
    	}
    	Citta b = boxCittaB.getValue();
    	if(b == null){
        	txtResult.appendText("Errore: selezionare la seconda citta.\n");
        	return;
        }
    	List<Ponte> ponti = new ArrayList<>(model.getPontiInComune(a, b));
    	txtResult.appendText("Ponti in comune:\n");
    	if(ponti.size()==0){
    		txtResult.appendText("Le due citta non hanno ponti in comune.\n");
    		return;
    	}
    	for(Ponte p : ponti){
    		txtResult.appendText(p.toString()+"\n");
    	}

    }

    @FXML
    void doCoperturaOttima(ActionEvent event) {

    	Citta a = boxCittaA.getValue();
    	if(a == null){
    		txtResult.appendText("Errore: selezionare la prima citta.\n");
    		return;
    	}
    	Citta b = boxCittaB.getValue();
    	if(b == null){
        	txtResult.appendText("Errore: selezionare la seconda citta.\n");
        	return;
        }
    	Citta c = boxCittaC.getValue();
    	if(c == null){
        	txtResult.appendText("Errore: selezionare la terza citta.\n");
        	return;
        }
    	
    	List<Ponte> ponti = model.avviaRicorsione(a, b, c);
    	txtResult.appendText("Numero minimo di ponti per coprire le 3 citta: "+ponti.size()+"\n");
    	txtResult.appendText("Ponti:\n");
    	for(Ponte p : ponti)
    	txtResult.appendText(p+"\n");
    }
    @FXML
    void calcolaBoxB(ActionEvent event) {
    	boxCittaB.getItems().clear();
    	List<Citta> boxB = model.getAllCitta();
    	boxB.remove(boxCittaA.getValue());
    	boxCittaB.getItems().addAll(boxB);
    	
    }

    @FXML
    void calcolaBoxC(ActionEvent event) {

    	boxCittaC.getItems().clear();
    	List<Citta> boxC = model.getAllCitta();
    	boxC.remove(boxCittaA.getValue());
    	boxC.remove(boxCittaB.getValue());
    	boxCittaC.getItems().addAll(boxC);
    }
    @FXML
    void initialize() {
        assert boxCittaA != null : "fx:id=\"boxCittaA\" was not injected: check your FXML file 'Radio.fxml'.";
        assert boxCittaB != null : "fx:id=\"boxCittaB\" was not injected: check your FXML file 'Radio.fxml'.";
        assert boxCittaC != null : "fx:id=\"boxCittaC\" was not injected: check your FXML file 'Radio.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Radio.fxml'.";
    }

	public void setModel(Model model) {
		this.model = model;
		boxCittaA.getItems().clear();
		boxCittaA.getItems().addAll(this.model.getAllCitta());
		
	}
}