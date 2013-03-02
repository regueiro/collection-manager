package es.regueiro.collectionManager.model;

public class Release {

	private String title;
	private String type;
	private String musicBrainzID;
	private String discogsURL;
	private int year;

	private boolean owned;
	private boolean downloading;
	private Quality quality;
	private String notes;

	public Release(String title) {
		super();
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the musicBrainzID
	 */
	public String getMusicBrainzID() {
		return musicBrainzID;
	}

	/**
	 * @param musicBrainzID
	 *            the musicBrainzID to set
	 */
	public void setMusicBrainzID(String musicBrainzID) {
		this.musicBrainzID = musicBrainzID;
	}

	/**
	 * @return the discogsURL
	 */
	public String getDiscogsURL() {
		return discogsURL;
	}

	/**
	 * @param discogsURL
	 *            the discogsURL to set
	 */
	public void setDiscogsURL(String discogsURL) {
		this.discogsURL = discogsURL;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the owned
	 */
	public boolean isOwned() {
		return owned;
	}

	/**
	 * @param owned
	 *            the owned to set
	 */
	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	/**
	 * @return the downloading
	 */
	public boolean isDownloading() {
		return downloading;
	}

	/**
	 * @param downloading
	 *            the downloading to set
	 */
	public void setDownloading(boolean downloading) {
		this.downloading = downloading;
	}

	/**
	 * @return the quality
	 */
	public Quality getQuality() {
		return quality;
	}

	/**
	 * @param quality
	 *            the quality to set
	 */
	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
