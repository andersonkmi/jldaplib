package org.sharpsw.ldap;

/**
 * User class. This class represents an existing user in the LDAP repository.
 * 
 * @author Anderson Ito
 * 
 */
public class User extends BaseLDAPElement implements Comparable<User> {
	private String displayName;
	private String sn = "";
	
	/**
	 * Class constructor that initializes the instance with the user id,
	 * password, common name and e-mail information.
	 * 
	 * @param uid
	 *            String containing the user id information.
	 * @param name
	 *            String containing the common name information.
	 * @param distinguishedName
	 *            String containing the DN information.
	 * @param commonName String containing the common name information.
	 */
	public User(final String uid, final String name, final String distinguishedName, final String commonName) {
		super(distinguishedName, uid, commonName);
		this.displayName = name;
	}
	
	/**
	 * Sets the display name of the user.
	 * @param value String containing the display name information.
	 */
	public final void setDisplayName(final String value) {
		this.displayName = value;
	}

	/**
	 * Gets the display name information.
	 * 
	 * @return String containing the common name.
	 */
	public final String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Returns the hash code information
	 * @return Integer number corresponding to the hash code.
	 */
	public final int hashCode() {
		return this.getId().hashCode() + this.displayName.hashCode()
				+ this.getDn().hashCode();
	}

	/**
	 * Verifies if two objects are equal.
	 * @return true if they are equal, false otherwise.
	 */
	public final boolean equals(final Object other) {
		if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
		}

		if (!this.getClass().equals(other.getClass())) {
			return false;
		}

		final User otherUser = (User) other;
		return this.getId().equals(otherUser.getId())
				&& this.getDn().equals(otherUser.getDn());
	}

	/**
	 * Returns a string representation of the object.
	 * @return String information.
	 */
	public final String toString() {
		return "User [uid = '" + this.getId() +
				"'; displayName = '" + this.displayName +
				"'; DN = '" + this.getDn() + "']";
	}
	

	/**
	 * Performs comparison between two <code>User</code> instances.
	 * @param user to compare against.
	 * @return Integer number indicating if the instances are equal or are lexicographically higher or lower.
	 */
	public int compareTo(User user) {
		return this.getCommonName().compareTo(user.getCommonName());
	}
}
