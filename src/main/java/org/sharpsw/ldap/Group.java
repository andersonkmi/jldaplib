package org.sharpsw.ldap;

import java.util.ArrayList;
import java.util.List;

import static org.sharpsw.ldap.GroupValidator.validate;

/**
 * Group - this class represents an existing group in the LDAP server.
 * 
 * @author Anderson Ito
 *
 */
public class Group extends BaseLDAPElement implements Comparable<Group> {
	private List<String> memberUids;
	private List<User> members;
	private String groupId;
			
	public Group(final String name, final String dn) {
		super(dn, name);
		this.memberUids = new ArrayList<>();
		this.members = new ArrayList<>();
	}
	
	public Group(final String name, final String dn, final String gid) {
		super(dn, name);
		this.memberUids = new ArrayList<>();
		this.groupId = gid;
	}


	public final List<User> getUserMembers() {
		return this.members;
	}
	
	public void setGroupId(final String gid) {
		this.groupId = gid;
	}
	
	public String getGroupId() {
		return this.groupId;				
	}

	/**
	 * Adds a member to the group represented as an instance of <code>User</code> class.
	 * @param member <code>User</code> instance.
	 * @see org.sharpsw.ldap.User
	 */
	public final void add(final User member) {
		validate(member);
		this.memberUids.add(member.getDn());
		this.members.add(member);
	}
	
	/**
	 * Adds a user member to the group given its uid information.
	 * @param uid String representing the user id information.
	 */
	public final void add(final String uid) {
		validate(uid);
		this.memberUids.add(uid);
	}
	
	/**
	 * Retrieves a list of strings that represent each member of the the group.
	 * @return List<String> instance containing the user ids of all group members.
	 */
	public final List<String> getMembers() {
		return new ArrayList<>(this.memberUids);
	}
	
	/**
	 * Returns the hash code of the object.
	 * @return Integer number representing the hash code.
	 */
	public final int hashCode() {
		return this.getId().hashCode();
	}
	
	/**
	 * Returns a string representation of the object.
	 * @return String containing some of the information.
	 */
	public final String toString() {
		StringBuilder info = new StringBuilder();
		info.append("Group [name = ").append(this.getId()).append("; members = ");
		for (String value : this.memberUids) {
			info.append(value);
			info.append(",");
		}
		info.append("]");
		return info.toString();
	}
	
	/**
	 * Verifies if the objects are equal.
	 * @return true if they are equal; false otherwise.
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
		
		Group otherGroup = (Group) other;
		return this.getDn().equals(otherGroup.getDn());

	}
	
	/**
	 * Performs the comparison between <code>Group</code> instances.
	 * @param group to compare against.
	 * @return Integer indicating if the instances are equal or lexiographically higher or lower.
	 */
	public int compareTo(Group group) {
		return this.getId().compareTo(group.getId());
	}
}
