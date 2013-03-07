package es.regueiro.collectionManager.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.model.Artist;

@Component
public class ArtistController {
	@Autowired
	private Validator validator;

	public ArtistController() {
	}

	public Artist createArtist(String name) {
		return this.createArtist(name, null, null, null);
	}

	public Artist createArtist(String name, String sortName,
			String musicBrainzID, String discogsURL) {

		StringBuilder errorMessage = new StringBuilder();

		Artist artist = new Artist(name);
		artist.setSortName(sortName);
		artist.setMusicBrainzID(musicBrainzID);

		if (!StringUtils.isEmpty(discogsURL)) {
			String http = "http://";
			if (!discogsURL.startsWith(http)) {
				discogsURL = http.concat(discogsURL);
			}

			try {
				artist.setDiscogsURL(new URL(discogsURL));
			} catch (MalformedURLException exc) {
				errorMessage.append("The entered discogs URL is invalid\n");
			}
		}

		Set<ConstraintViolation<Artist>> constraintViolations = validator
				.validate(artist);

		if (!constraintViolations.isEmpty()) {
			for (ConstraintViolation<Artist> c : constraintViolations) {
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
