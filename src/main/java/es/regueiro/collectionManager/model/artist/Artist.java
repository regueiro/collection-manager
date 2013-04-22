//TODO: Comment
package es.regueiro.collectionManager.model.artist;

import java.util.Collections;
import java.util.List;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
//import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import es.regueiro.collectionManager.model.release.ReleaseGroup;

public abstract class Artist {

	private long id;
	protected String name;
	private String sortName;
	private List<ReleaseGroup> releaseGroupList;

	public Artist() {
	}

	public Artist(String name) {
		super();
		this.name = name;
	}

	@NotBlank(message = "{artistName.notBlank}")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!StringUtils.isBlank(name)) {
			this.name = name;
		} else {
			this.name = null;
		}
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		if (!StringUtils.isBlank(sortName)) {
			this.sortName = sortName;
		} else {
			this.sortName = null;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ReleaseGroup> getReleaseGroupList() {
		return Collections.unmodifiableList(releaseGroupList);
	}

	public void addReleaseGroup(ReleaseGroup release) {
		this.releaseGroupList.add(release);
	}

	public void removeReleaseGroup(ReleaseGroup release) {
		this.releaseGroupList.remove(release);
	}

	public boolean hasReleaseGroup(ReleaseGroup release) {
		return this.releaseGroupList.contains(release);
	}

	@Override
	public String toString() {
		return name;
	}
}