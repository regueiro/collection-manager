package es.regueiro.collectionManager.ui;

import java.util.List;

import javax.swing.AbstractListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.library.Library;
import es.regueiro.collectionManager.model.Artist;

@Component
public class ArtistListModel extends AbstractListModel<Artist> {

	private static final long serialVersionUID = -1740023598708389850L;
	@Autowired
	private Library library;

	public ArtistListModel() {
	}

	@Override
	public int getSize() {
		return library.getArtistList().size();
	}

	@Override
	public Artist getElementAt(int index) {
		return library.getArtistList().get(index);
	}

	public void clear() {
		int end = this.getSize();
		library.clear();
		this.fireIntervalRemoved(this, 0, end);
	}

	public Artist getArtist(String name) {
		for (Artist artist : library.getArtistList()) {
			if (artist.getName().equals(name)) {
				return artist;
			}
		}
		return null;
	}

	public List<Artist> getArtistList() {
		return library.getArtistList();
	}

	public void addArtist(Artist artist) {
		library.addArtist(artist);

		this.fireIntervalAdded(this, library.getArtistList().indexOf(artist),
				library.getArtistList().indexOf(artist));
	}

	public void removeArtist(Object object) {
		Artist artist = (Artist) object;

		if (artist != null) {
			int index = library.getArtistList().indexOf(artist);
			library.removeArtist(artist);
			this.fireIntervalRemoved(this, index, index);
		}

	}

}
