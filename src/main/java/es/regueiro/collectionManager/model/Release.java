package es.regueiro.collectionManager.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import es.regueiro.collectionManager.utils.validator.NullOrNotBlank;
import es.regueiro.collectionManager.utils.validator.NullOrPattern;

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
	private boolean owned;
	private boolean pending;
	private boolean ignored;
	private Quality quality;
	private String notes;

	/**
	 * Instantiates a new release.
	 * 
	 * @param title
	 *            the title
	 */
	public Release(String title) {
		super();
		this.title = title;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	@NotBlank(message = "The title of the release can't be empty")
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
		this.title = title;
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
		this.type = type;
	}

	/**
	 * Gets the music brainz id.
	 * 
	 * @return the musicBrainzID
	 */
	@NullOrPattern(regexp = "[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}", message = "The MusicBrainz ID must be a valid ID")
	public String getMusicBrainzID() {
		return musicBrainzID;
	}

	/**
	 * Sets the music brainz id.
	 * 
	 * @param musicBrainzID
	 *            the musicBrainzID to set
	 */
	public void setMusicBrainzID(String musicBrainzID) {
		this.musicBrainzID = musicBrainzID;
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
	@NullOrPattern(regexp = "[0-9]{4}", message = "The year must consist of 4 digits")
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
		this.year = null;
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
	@NullOrNotBlank(message = "The notes can't be an empty string")
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
