package es.regueiro.collectionManager.musicbrainz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.musicbrainz.services.ArtistService;

@Component
public class MusicBrainzManager {

	@Autowired
	private ArtistService artistService;
	

	@Autowired
	private MusicBrainzRestService restService;

	public MusicBrainzManager() {
	}

	public void setAuthentication(String username, String sha1password) {
		restService.setAuthentication(username, sha1password);
	}

	public ArtistService artistService() {
		return artistService;
	}
	
}
