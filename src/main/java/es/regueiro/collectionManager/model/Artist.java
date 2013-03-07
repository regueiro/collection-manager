package es.regueiro.collectionManager.model;

import java.net.URL;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import es.regueiro.collectionManager.utils.validator.NullOrNotBlank;
import es.regueiro.collectionManager.utils.validator.NullOrPattern;

// TODO: Auto-generated Javadoc
/**
 * The Class Artist.
 */
public class Artist {

	private long id;
	private String name;
	private String sortName;
	private String musicBrainzID;
	private URL discogsURL;
	private List<Release> releaseList;

	/**
	 * Instantiates a new artist.
	 * 
	 * @param name
	 *            the name
	 */
	public Artist(String name) {
		super();
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	@NotBlank(message = "{artistName.notBlank}")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the sort name.
	 * 
	 * @return the sort name
	 */
	@NullOrNotBlank(message = "{artistSortName.notBlank}")
	public String getSortName() {
		return sortName;
	}

	/**
	 * Sets the sort name.
	 * 
	 * @param sortName
	 *            the new sort name
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the release list.
	 * 
	 * @return the release list
	 */
	public List<Release> getReleaseList() {
		return releaseList;
	}

	/**
	 * Sets the release list.
	 * 
	 * @param releaseList
	 *            the new release list
	 */
	public void setReleaseList(List<Release> releaseList) {
		this.releaseList = releaseList;
	}

	/**
	 * Gets the music brainz id.
	 * 
	 * @return the music brainz id
	 */
	@NullOrPattern(message = "{artistMBID.notValid}", regexp = "[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}")
	public String getMusicBrainzID() {
		return musicBrainzID;
	}

	/**
	 * Sets the music brainz id.
	 * 
	 * @param musicBrainzID
	 *            the new music brainz id
	 */
	public void setMusicBrainzID(String musicBrainzID) {
		this.musicBrainzID = musicBrainzID;
	}

	/**
	 * Gets the discogs url.
	 * 
	 * @return the discogs url
	 */
	public URL getDiscogsURL() {
		return discogsURL;
	}

	/**
	 * Sets the discogs url.
	 * 
	 * @param discogsURL
	 *            the new discogs url
	 */
	public void setDiscogsURL(URL discogsURL) {
		this.discogsURL = discogsURL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}