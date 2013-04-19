package es.regueiro.collectionManager.model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import es.regueiro.collectionManager.model.collection.Collection;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "username", nullable = false, unique = true)
	@NotEmpty
	private String username;
	@Column(name = "password", nullable = false)
	@NotEmpty
	private String password;
	@Column(name = "email", nullable = false, unique = true)
	@NotEmpty
	@Email
	private String email;
	@ElementCollection
	@CollectionTable(name = "ROLES", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name="user_role", nullable = false)
	//@OrderColumn // Hacky way to create a primary key for the collection table
	@Type(type = "es.regueiro.collectionManager.util.EnumUserType", parameters = @Parameter(name = "type", value = "es.regueiro.collectionManager.model.user.Role"))
	private List<Role> roleList = new ArrayList<Role>();
	@Transient
	private List<Collection> collectionList;

	/**
	 * @return the name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> role) {
		this.roleList = role;
	}

	/**
	 * @return the collectionList
	 */
	public List<Collection> getCollectionList() {
		return collectionList;
	}

	/**
	 * @param collectionList
	 *            the collectionList to set
	 */
	public void setCollectionList(List<Collection> collectionList) {
		this.collectionList = collectionList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		return username.equals(user.username);
	}

	@Override
	public int hashCode() {
		return 13 * username.hashCode();
	}
}
