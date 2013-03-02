package es.regueiro.collectionManager.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.regueiro.collectionManager.model.Artist;

public class ColectionManager {
	private List<Artist> collection;

	public ColectionManager() {
		this.collection = new ArrayList<Artist>();
	}

	public void addArtist(Artist artist) {
		if (!this.collection.contains(artist)) {
			this.collection.add(artist);
		} else {
			throw new IllegalArgumentException(
					"The artist already exists in the database");
		}
	}

	public void removeArtist(Artist artist) {
		this.collection.remove(artist);
	}

	public void removeArtist(String name) {
		Iterator<Artist> it = this.collection.iterator();
		while (it.hasNext()) {
			Artist artist = it.next();
			if (artist.getName().equals(name)) {
				it.remove();
			}
		}
	}

	public boolean hasArtist(Artist artist) {
		if (this.collection.contains(artist)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasArtist(String name) {
		for (Artist artist : this.collection) {
			if (artist.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
