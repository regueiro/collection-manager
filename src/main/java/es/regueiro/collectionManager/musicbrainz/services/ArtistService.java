package es.regueiro.collectionManager.musicbrainz.services;

import org.musicbrainz.mmd2.Artist;
import org.musicbrainz.mmd2.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.musicbrainz.MusicBrainzRestService;;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountService.
 */
@Component
public class ArtistService {

	/** The rest service. */
	@Autowired
	private MusicBrainzRestService restService;

	/**
	 * Returns all settings for the authenticated user. Use these settings to
	 * customize your app based on the user's settings. For example, if they use
	 * advanced ratings show a 10 heart scale. If they prefer simple ratings,
	 * show the binary scale. The social connections are also useful to
	 * customize the checkin prompt.
	 * 
	 * @return the string
	 */
	public String settings() {

		return restService.post("account/settings", String.class, null, null);
	}

	/**
	 * Test trakt credentials. This is useful for your configuration screen and
	 * is a simple way to test someone's trakt account.
	 * 
	 * @return the string
	 */
	public String test() {
		return restService.search("artist", String.class, "artist", "fred");

	}
	
	public Artist audioslave() {
		Metadata data = restService.lookup("artist", "020bfbb4-05c3-4c86-b372-17825c262094", Metadata.class);
		return data.getArtist();
	}

	/**
	 * Test api.
	 * 
	 * @return the string
	 */
	public String testAPI() {
		return restService.get("movie/summary.json", String.class, "tt1285016");
	}
}
