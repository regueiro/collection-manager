//TODO comment
package es.regueiro.collectionManager.persistency;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.regueiro.collectionManager.model.artist.Artist;

@Repository
public interface ArtistDAO {

	public void addArtist(Artist artist);

	public Artist getArtistById(long id);

	public void saveArtist(Artist artist);
	
	public List<Artist> listAllArtists();
}
