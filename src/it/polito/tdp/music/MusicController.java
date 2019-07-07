package it.polito.tdp.music;

import java.net.URL;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.ArtistFrequency;
import it.polito.tdp.music.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {

	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Month> boxMese;

	@FXML
	private Button btnArtisti;

	@FXML
	private Button btnNazioni;

	@FXML
	private TextArea txtResult;

	@FXML
	void doDistanzaMax(ActionEvent event) {

		Month mese = boxMese.getValue();
		
		if(mese == null) {
			txtResult.appendText("Selezionare un mese!");
			return;
		}
		
		
		model.getTopCountries(mese);
		model.creaGrafo(mese);
		
		txtResult.appendText("\nLa distanza massima e': "+model.maxDistanzaCountry());
		
	}

	@FXML
	void doElenco(ActionEvent event) {

		txtResult.clear();

		Month mese = boxMese.getValue();

		if(mese==null) {
			txtResult.appendText("Selezionare un mese!");
		}
		else {

			List<ArtistFrequency> topArtisti = model.doElenco(mese);


			for(ArtistFrequency a : topArtisti) {
				txtResult.appendText("Id: "+a.getId()+" Nome: "+a.getNome()+" Frequenza: "+a.getFrequenza()+"\n");
			}
		}

	}

	@FXML
	void initialize() {
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert btnArtisti != null : "fx:id=\"btnArtisti\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert btnNazioni != null : "fx:id=\"btnNazioni\" was not injected: check your FXML file 'MusicA.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicA.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		boxMese.getItems().addAll(model.getMonths());
	}
}
