/******************************************************************************
    JLdapLib - Simple LDAP library for Java.
    Copyright (C) 2010  Anderson Ito (andersonkmi@acm.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
******************************************************************************/

//***************************************************************************
//*** Modification history
//***
//*** Date			Author			Description
//*** ====			======			===========
//*** Jan/07/2010	andersonkmi		Code documentation included and logging.
//*** Apr/27/2010	andersonkmi		Removed logging code.
//****************************************************************************

package org.sharpsw.ldap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		this.memberUids = new ArrayList<String>();		
		this.members = new ArrayList<User>();
	}
	
	public Group(final String name, final String dn, final String gid) {
		super(dn, name);
		this.memberUids = new ArrayList<String>();
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
		this.memberUids.add(member.getDn());
		this.members.add(member);
	}
	
	/**
	 * Adds a user member to the group given its uid information.
	 * @param uid String representing the user id information.
	 */
	public final void add(final String uid) {
		this.memberUids.add(uid);
	}
	
	/**
	 * Retrieves a list of strings that represent each member of the the group.
	 * @return List<String> instance containing the user ids of all group members.
	 */
	public final List<String> getMembers() {
		List<String> users = new ArrayList<String>();
		Iterator<String> iterator = this.memberUids.iterator();
		
		while (iterator.hasNext()) {
			users.add(iterator.next());
		}
		
		return users;
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
		StringBuffer info = new StringBuffer();
		info.append("Group [name = ").append(this.getId()).append("; members = ");
		Iterator<String> iter = this.memberUids.iterator();
		while (iter.hasNext()) {
			info.append(iter.next());
			
			// Verifies if there is a next element
			if (iter.hasNext()) {
				info.append(",");
			}
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
	 * @param Group to compare against.
	 * @return Integer indicating if the instances are equal or lexiographically higher or lower.
	 */
	public int compareTo(Group group) {
		return this.getId().compareTo(group.getId());
	}
}
