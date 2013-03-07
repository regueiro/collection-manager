package es.regueiro.collectionManager.library;

import java.util.List;

import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.model.Artist;

@Component
public interface Library {

	public void addArtist(Artist artist);

	public void removeArtist(Artist artist);
	
	public void removeArtist(String name);

	public boolean hasArtist(Artist artist);
	
	public boolean hasArtist(String name);

	public List<Artist> getArtistList();

}
