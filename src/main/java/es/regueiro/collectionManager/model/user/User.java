package es.regueiro.collectionManager.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import es.regueiro.collectionManager.model.collection.Collection;

/**
 * The user of the application
 */
@Entity
@Table(name = "USER")
public class User {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The username. */
	@Column(name = "username", nullable = false, unique = true)
	@NotEmpty
	private String username;

	/** The password. */
	@Column(name = "password", nullable = false)
	@NotEmpty
	private String password;

	/** The email. */
	@Column(name = "email", nullable = false, unique = true)
	@NotEmpty
	@Email
	private String email;

	/** The role list. */
	@ElementCollection
	@CollectionTable(name = "ROLES",
			joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "user_role", nullable = false)
	// @OrderColumn // Hacky way to create a primary key for the collection
	// table
	@Type(type = "es.regueiro.collectionManager.util.EnumUserType",
			parameters = @Parameter(name = "type",
					value = "es.regueiro.collectionManager.model.user.Role"))
	private List<Role> roleList = new ArrayList<Role>();

	/** The collection list. */
	@Transient
	private List<Collection> collectionList;

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the role list.
	 * 
	 * @return the role list
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * Sets the role list.
	 * 
	 * @param role
	 *            the new role list
	 */
	public void setRoleList(List<Role> role) {
		this.roleList = role;
	}

	/**
	 * Gets the collection list.
	 * 
	 * @return the collectionList
	 */
	public List<Collection> getCollectionList() {
		return collectionList;
	}

	/**
	 * Sets the collection list.
	 * 
	 * @param collectionList
	 *            the collectionList to set
	 */
	public void setCollectionList(List<Collection> collectionList) {
		this.collectionList = collectionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		return username.equals(user.username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 13 * username.hashCode();
	}
}
