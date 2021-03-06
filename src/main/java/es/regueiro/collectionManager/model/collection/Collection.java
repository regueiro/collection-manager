//TODO Comment
package es.regueiro.collectionManager.model.collection;

import java.util.List;

import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.model.artist.Artist;

@Component
public interface Collection {

	public void addArtist(Artist artist);

	public void removeArtist(Artist artist);
	
	public void removeArtist(String name);

	public boolean hasArtist(Artist artist);
	
	public boolean hasArtist(String name);

	public List<Artist> getArtistList();

	public void clear();

}
