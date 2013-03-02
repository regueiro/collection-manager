package es.regueiro.collectionManager.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.controller.ArtistController;
import es.regueiro.collectionManager.model.Artist;

@Component
public class ArtistListModel extends AbstractListModel<Artist> {

	@Autowired
	private ArtistController artistController;
	
	public ArtistListModel() {
	}
	
	@Override
	public int getSize() {
		return artistController.getArtistList().size();
	}

	@Override
	public Artist getElementAt(int index) {
		return artistController.getArtistList().get(index);
	}

	public void clear() {
		int end = this.getSize();
		artistController.getArtistList().clear();
		this.fireIntervalRemoved(this, 0, end);
	}


	public Artist getArtist(String name) {
		for (Artist artist:artistController.getArtistList()) {
			if (artist.getName().equals(name)) {
				return artist;
			}
		}
		return null;
	}
	
	public List<Artist> getArtistList() {
		return artistController.getArtistList();
	}

	public void addArtist(Artist artist) {
		artistController.getArtistList().add(artist);

		this.fireIntervalAdded(this, artistController.getArtistList().indexOf(artist), artistController.getArtistList().indexOf(artist));
	}

	public void removeArtist(Object object) {
		Artist artist = (Artist) object;
		
		if (artist != null) {
			int index = artistController.getArtistList().indexOf(artist);
			artistController.getArtistList().remove(index);
			this.fireIntervalRemoved(this, index, index);
		}
		
	}

}
