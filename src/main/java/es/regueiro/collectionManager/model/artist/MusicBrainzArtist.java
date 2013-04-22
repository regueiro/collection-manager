//TODO Comment
package es.regueiro.collectionManager.model.artist;

import es.regueiro.collectionManager.utils.validator.NullOrPattern;
import es.regueiro.collectionManager.utils.validator.ValidationUtils;

public class MusicBrainzArtist extends Artist {

	private String musicBrainzId;

	public MusicBrainzArtist() {

	}

	public MusicBrainzArtist(String name) {
		super(name);
	}

	public MusicBrainzArtist(org.musicbrainz.mmd2.Artist mbArtist) {
		this.setName(mbArtist.getName());
		this.setSortName(mbArtist.getSortName());
		this.setMusicBrainzId(mbArtist.getId());
		//TODO: Add release list
	}

	@NullOrPattern(message = "{artistMBID.notValid}", regexp = ValidationUtils.mbPattern)
	public String getMmusicBrainzId() {
		return musicBrainzId;
	}

	public void setMusicBrainzId(String value) {
		this.musicBrainzId = value;
	}

}
