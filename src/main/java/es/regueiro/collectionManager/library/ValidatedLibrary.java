package es.regueiro.collectionManager.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.model.Artist;

@Component
public class ValidatedLibrary implements Library {

	@Autowired
	private Validator validator;
	private List<Artist> artistList;

	public ValidatedLibrary() {
		this.artistList = new ArrayList<Artist>();
	}

	@Override
	public void addArtist(@Valid Artist artist) {

		if (validator.validate(artist).isEmpty()) {
			if (!this.hasArtist(artist)) {
				this.artistList.add(artist);
			} else {
				throw new IllegalArgumentException(
						"The artist already exists in the database");
			}
		} else {
			throw new IllegalArgumentException("The provided artist is invalid");
		}

	}

	@Override
	public void removeArtist(Artist artist) {
		this.artistList.remove(artist);
	}

	@Override
	public void removeArtist(String name) {
		Iterator<Artist> iter = artistList.iterator();
		while (iter.hasNext()) {
			Artist artist = iter.next();
			if (artist.getName().equals(name)) {
				iter.remove();
			}
		}
	}

	@Override
	public boolean hasArtist(Artist artist) {
		return artistList.contains(artist);
	}

	@Override
	public boolean hasArtist(String name) {
		for (Artist artist : artistList) {
			if (artist.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Artist> getArtistList() {
		return artistList;
	}

}
