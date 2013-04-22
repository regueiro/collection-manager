package es.regueiro.collectionManager.model.user;

/**
 * The roles an user can perform.
 */
public enum Role {

	USER("user", "User"),
	ADMIN("admin", "Administrator");

	/** The role name. */
	private String name;

	/** The display name for the role. */
	private String displayName;

	/**
	 * Creates a new role.
	 * 
	 * @param name
	 *            the name
	 * @param displayName
	 *            the display name
	 */

	Role(String name, String displayName) {
		this.name = name;
		this.setDisplayName(displayName);
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
	 * Gets the display name.
	 * 
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 * 
	 * @param displayName
	 *            the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * Gets a role by name.
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