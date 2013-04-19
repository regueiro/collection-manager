package es.regueiro.collectionManager.model.user;

import javax.persistence.Embeddable;

// TODO: Auto-generated Javadoc
/**
 * The Enum Role.
 */
public enum Role {

	USER("User"), ADMIN("Admin");

	private String name;

	/**
	 * Instantiates a new quality.
	 * 
	 * @param name
	 *            the name
	 */

	Role(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Equals.
	 * 
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public boolean equals(String name) {
		return this.name.equals(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Gets the.
	 * 
	 * @param name
	 *            the name
	 * @return the role
	 */
	public static Role get(String name) {
		for (Role role : Role.values()) {
			if (role.name.equals(name)) {
				return role;
			}
		}
		return null;
	}
}