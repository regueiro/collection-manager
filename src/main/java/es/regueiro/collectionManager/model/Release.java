package es.regueiro.collectionManager.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import es.regueiro.collectionManager.utils.validator.NullOrPattern;
import es.regueiro.collectionManager.utils.validator.ValidationUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Release.
 */
public class Release {

	private String title;
	private String type;
	private String musicBrainzID;
	private String discogsURL;
	private String year;
	private boolean owned = false;
	private boolean pending = false;
	private boolean ignored = false;
	private Quality quality;
	private String notes;
	private Artist artist;

	/**
	 * Instantiates a new release.
	 * 
	 * @param title
	 *            the title
	 */
	public Release(Artist artist, String title) {
		super();
		this.artist = artist;
		this.title = title;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	@NotBlank(message = "{releaseTitle.notBlank}")
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if (!StringUtils.isBlank(title)) {
			this.title = title;
		} else {
			this.title = null;
		}
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		if (!StringUtils.isBlank(type)) {
			this.type = type;
		} else {
			this.type = null;
		}
	}

	/**
	 * Gets the musicbrainz id.
	 * 
	 * @return the musicBrainzID
	 */
	@NullOrPattern(regexp = ValidationUtils.mbPattern, message = "{releaseMBID.notValid}")
	public String getMusicBrainzID() {
		return musicBrainzID;
	}

	/**
	 * Sets the musicbrainz id.
	 * 
	 * @param musicBrainzID
	 *            the musicBrainzID to set
	 */
	public void setMusicBrainzID(String musicBrainzID) {
		if (!StringUtils.isBlank(musicBrainzID)) {
			this.musicBrainzID = musicBrainzID;
		} else {
			this.musicBrainzID = null;
		}
	}

	/**
	 * Gets the discogs url.
	 * 
	 * @return the discogsURL
	 */
	public String getDiscogsURL() {
		return discogsURL;
	}

	/**
	 * Sets the discogs url.
	 * 
	 * @param discogsURL
	 *            the discogsURL to set
	 */
	public void setDiscogsURL(String discogsURL) {
		this.discogsURL = discogsURL;
	}

	/**
	 * Gets the year.
	 * 
	 * @return the year
	 */
	@NullOrPattern(regexp = "[0-9]{4}", message = "{releaseYear.4digits}")
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 * 
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		if (!StringUtils.isBlank(year)) {
			this.year = year;
		} else {
			this.year = null;
		}
	}

	/**
	 * Checks if is owned.
	 * 
	 * @return the owned
	 */
	public boolean isOwned() {
		return owned;
	}

	/**
	 * Sets the owned.
	 * 
	 * @param owned
	 *            the owned to set
	 */
	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	/**
	 * Checks if is pending.
	 * 
	 * @return the pending
	 */
	public boolean isPending() {
		return pending;
	}

	/**
	 * Sets the pending.
	 * 
	 * @param pending
	 *            the pending to set
	 */
	public void setPending(boolean pending) {
		this.pending = pending;
	}

	/**
	 * Checks if is ignored.
	 * 
	 * @return the ignored
	 */
	public boolean isIgnored() {
		return ignored;
	}

	/**
	 * Sets the ignored.
	 * 
	 * @param ignored
	 *            the ignored to set
	 */
	public void setIgnored(boolean ignored) {
		this.ignored = ignored;
	}

	/**
	 * Gets the quality.
	 * 
	 * @return the quality
	 */
	public Quality getQuality() {
		return quality;
	}

	/**
	 * Sets the quality.
	 * 
	 * @param quality
	 *            the quality to set
	 */
	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	/**
	 * Gets the notes.
	 * 
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		if (!StringUtils.isBlank(notes)) {
			this.notes = notes;
		} else {
			this.notes = null;
		}
	}

	/**
	 * Gets the artist.
	 *
	 * @return the artist
	 */
	@NotNull(message = "{releaseArtist.notNull}")
	public Artist getArtist() {
		return artist;
	}

}
