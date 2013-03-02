package es.regueiro.collectionManager.ui.controller;

import java.net.URL;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.controller.ArtistController;
import es.regueiro.collectionManager.model.Artist;
import es.regueiro.collectionManager.ui.ArtistListModel;

@Component
public class MainWindowController {

	@Autowired
	private ArtistListModel artistListModel;
	@Autowired
	private ArtistController artistController;
	private Validator validator;

	public MainWindowController() {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public void populate() {
		String[] values = new String[] { "Fortitude", "Adoration of the Magi",
				"The Birth of Venus", "Primavera", "Cestello Annunciation",
				"St. Augustine", "Venus and Mars", "Mystical Nativity",
				"Temptation of Christ" };
		for (String s : values) {
			Artist art = new Artist(s);

			artistListModel.addArtist(art);
		}
		
	}
	

	public ArtistListModel getArtistListModel() {
		return artistListModel;
	}

	// public void addArtist(String name, String sortName, String musicBrainzID,
	// URL discogsURL) {
	// if (StringUtils.isEmpty(name)) {
	// throw new IllegalArgumentException("The artist name can not be null");
	// } else {
	// Artist artist = new Artist(name);
	// artist.setSortName(sortName);
	// artist.setMusicBrainzID(musicBrainzID);
	// artist.setDiscogsURL(discogsURL);
	//
	// artistListModel.addArtist(artist);
	//
	// }
	//
	//
	// }

	public void addArtist(String name, String sortName, String musicBrainzID,
			String discogsURL) {

		Artist artist = artistController.createArtist(name, sortName, musicBrainzID, discogsURL);
		
		artistListModel.addArtist(artist);
	}

	public void editArtist(Artist artist, String name, String sortName,
			String musicBrainzID, URL discogsURL) {

	}

	public void removeArtist(Object selectedValue) {
		artistListModel.removeArtist(selectedValue);
	}
}
