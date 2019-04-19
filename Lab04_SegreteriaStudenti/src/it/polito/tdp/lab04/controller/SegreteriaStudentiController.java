package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> menuTendina = new ComboBox<String>();

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnTick;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnReset;
    
    
    public void setModel(Model model) {
    	this.model = model;
    	this.riempiTendina();
    }

    @FXML
    void doClearText(ActionEvent event) {

    }

    @FXML
    void doCompleteText(ActionEvent event) {
        String matricola = txtMatricola.getText();
        txtCognome.clear();
        txtNome.clear();
        if(matricola.compareTo("")==0) {
        	txtOutput.appendText("Devi inserire la  matricola\n");
        }
        else {
        	Studente s = this.model.getDatiPersona(matricola);
        	if(s==null) {
        		txtOutput.appendText("Lo studente non è presente!");
        	}
        	else {
        	txtNome.appendText(s.getNome());
        	txtCognome.appendText(s.getCognome());
        	}
        }
        
    }

    @FXML
    void doIscriviText(ActionEvent event) {
    	 String matricola = txtMatricola.getText();
         Studente s = this.model.getDatiPersona(matricola);
         String corso = menuTendina.getValue();
         if(corso.compareTo("")==0) {
        	 txtOutput.appendText("Devi selezionare un corso\n");
         }
     	if(s==null) {
     		txtOutput.appendText("Lo studente non è presente!\n");
     	}
     	else
     	{
     		if(this.model.iscriviStudente(matricola, corso)) {
     			txtOutput.appendText("Ho iscritto lo studente al corso!\n");
     		}
     		else {
     			txtOutput.appendText("Lo studente e' già iscritto al corso\n");
     		}
     	}

    }

    @FXML
    void doSearchCourse(ActionEvent event) {
        String matricola = txtMatricola.getText();
        Studente s = this.model.getDatiPersona(matricola);
    	if(s==null) {
    		txtOutput.appendText("Lo studente non è presente!");
    	}
    	else {
    		List<Corso> l = this.model.getCorsi(matricola);
    		if(l.size()==0) {
    			txtOutput.appendText("Lo studente non e' iscritto a nessun corso");
    		}
    		else {
    			for(Corso c : l) {
    				txtOutput.appendText(c+"\n");
    			}
    		}
    	}

    }
    
    @FXML
    void doSearchPeopleText(ActionEvent event) {
       String corso = menuTendina.getValue();
       String matricola = txtMatricola.getText();
       if(matricola.compareTo("")==0) {
    	   if(corso.compareTo("")==0) {
        	   txtOutput.appendText("Devi selezionare un corso!!\n");
           }
           else {
           List<Studente> listaStudenti = this.model.getIscrittiCorso(corso);
           for(Studente s: listaStudenti) {
        	   txtOutput.appendText(s +"\n");
           }
           }
       }
       else {
    	   Studente s = this.model.getDatiPersona(matricola);
       	if(s==null) {
       		txtOutput.appendText("Lo studente non è presente!");
       	}
       	else {
       		if(this.model.isIscritto(matricola, corso)) {
       			txtOutput.appendText("Lostudente e' iscritto al corso: "+corso);
       		}
       		else {
       			txtOutput.appendText("Lo studente non e' iscritto a quel corso!");
       		}
       	}
       }
      
    }

    @FXML
    void initialize() {
        assert menuTendina != null : "fx:id=\"menuTendina\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnTick != null : "fx:id=\"btnTick\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
    }
   
    public void riempiTendina() {
    	/*for(String s : this.model.getNomeCorso()) {
    		System.out.println(s);
    	}*/
    	menuTendina.setEditable(false);
    	menuTendina.setItems(FXCollections.observableArrayList(this.model.getNomeCorso()));
    }
    
}
