package es.regueiro.collectionManager.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.model.Artist;

@Component
public class ArtistController {
	private Validator validator;
	private List<Artist> data;
	
	

	public ArtistController() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
		data = new ArrayList<Artist>();
	}
		
	public List<Artist> getArtistList() {
		return data;
	}
	
	public void addArtist(Artist artist) {
		data.add(artist);
	}
	
	public Artist createArtist(String name, String sortName, String musicBrainzID,
			String discogsURL) {

		StringBuilder errorMessage = new StringBuilder();
		
		Artist artist = new Artist(name);
		artist.setSortName(sortName);
		artist.setMusicBrainzID(musicBrainzID);
		
		String http = "http://";
		if (!discogsURL.startsWith(http)) {
			discogsURL = http.concat(discogsURL);					
		}
		
		try {
			artist.setDiscogsURL(new URL(discogsURL));
		} catch (MalformedURLException exc) {
			errorMessage.append("The entered discogs URL is invalid\n");
		}

		Set<ConstraintViolation<Artist>> constraintViolations =  validator.validate(artist);
		
		
		if (!constraintViolations.isEmpty()) {
			for (ConstraintViolation<Artist> c:constraintViolations) {
				errorMessage.append(c.getMessage());
				errorMessage.append("\n");
			}
		}
			
			
		if (errorMessage.length() > 0) {
			throw new IllegalArgumentException(errorMessage.toString());
		} else {
			return artist;
		}
	}
}
