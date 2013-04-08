package es.regueiro.collectionManager.model;

import java.util.Collections;
import java.util.List;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
//import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import es.regueiro.collectionManager.utils.validator.NullOrPattern;
import es.regueiro.collectionManager.utils.validator.ValidationUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Artist.
 */
/**
 * @author santi
 * 
 */
//@Entity
public class Artist {

//	@Id
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name="increment", strategy = "increment")
	private long id;
	private String name;
//	@Transient
	private String sortName;
//	@Transient
	private String musicBrainzID;
//	@Transient
	private List<Release> releaseList;

	public Artist() {
		
	}
	
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
		if (!StringUtils.isBlank(name)) {
			this.name = name;
		} else {
			this.name = null;
		}
	}

	/**
	 * Gets the sort name.
	 * 
	 * @return the sort name
	 */
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
		if (!StringUtils.isBlank(sortName)) {
			this.sortName = sortName;
		} else {
			this.sortName = null;
		}
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
		return Collections.unmodifiableList(releaseList);
	}

	/**
	 * Adds a new release.
	 * 
	 * @param release
	 *            the release
	 */
	public void addRelease(Release release) {
		this.releaseList.add(release);
	}

	/**
	 * Removes the release.
	 * 
	 * @param release
	 *            the release
	 */
	public void removeRelease(Release release) {
		this.releaseList.remove(release);
	}

	/**
	 * Checks for release.
	 * 
	 * @param release
	 *            the release
	 * @return true, if successful
	 */
	public boolean hasRelease(Release release) {
		return this.releaseList.contains(release);
	}

	/**
	 * Gets the musicbrainz id.
	 * 
	 * @return the musicbrainz id
	 */
	@NullOrPattern(message = "{artistMBID.notValid}", regexp = ValidationUtils.mbPattern)
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
		if (!StringUtils.isBlank(musicBrainzID)) {
			this.musicBrainzID = musicBrainzID;
		} else {
			this.musicBrainzID = null;
		}
	}

	/**
	 */
	}

	/**
	 */
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