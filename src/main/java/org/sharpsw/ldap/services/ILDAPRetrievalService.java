package org.sharpsw.ldap.services;

import java.util.List;

import org.sharpsw.ldap.Group;
import org.sharpsw.ldap.User;
import org.sharpsw.ldap.services.exception.LDAPFindException;

/**
 * Defines the operations related to the retrieval of information from the LDAP server.
 * @author Anderson Ito
 *
 */
public interface ILDAPRetrievalService extends ILDAPBaseService {
	/**
	 * Obtains the user given its uid and the initial base DN.
	 * @param uid User id to be searched.
	 * @param baseDN Base DN to start the search.
	 * @return Instance of the class <code>User</code> containing its information.
	 * @throws LDAPFindException If the user is not found.
	 */
	User getUser(final String uid, final String baseDN)	throws LDAPFindException;

	/**
	 * Searches for an user based on its uid.
	 * @param uid String containing the user id.
	 * @return Instance of the class <code>User</code>.
	 * @throws LDAPFindException If the user is not found.
	 */
	User getUser(final String uid) throws LDAPFindException;
	
	/**
	 * Obtains all users in the LDAP server.
	 * @param baseDN Base DN to start the search.
	 * @return A list containing instances of the class <code>User</code> for each element found in the LDAP server.
	 * @throws LDAPFindException If nothing is found.
	 */
	List<User> getAllUsers(final String baseDN) throws LDAPFindException;

	/**
	 * Returns a list of all users in the LDAP server.
	 * @return List of instances of the class <code>User</code> for each user found in the server.
	 * @throws LDAPFindException If nothing is found.
	 */
	List<User> getAllUsers() throws LDAPFindException;

	/**
	 * Returns a list of groups found in the LDAP server.
	 * @param baseDN Base DN to start the search.
	 * @return List of instances of the class <code>Group</code> for each group found.
	 * @throws LDAPFindException If noting is found.
	 */
	List<Group> getGroups(final String baseDN) throws LDAPFindException;

	/**
	 * Returns a list of groups in the LDAP server.
	 * @return List of instances of the class <code>Group</code>.
	 * @throws LDAPFindException If nothing is found.
	 */
	List<Group> getGroups() throws LDAPFindException;

	/**
	 * Returns a group based on the information supplied.
	 * @param groupName Name of the group.
	 * @param baseDN Base DN to start the search.
	 * @return Instance of the class <code>Group</code>.
	 * @throws LDAPFindException If nothing is found.
	 */
	Group getGroup(final String groupName, final String baseDN)	throws LDAPFindException;

	/**
	 * Returns a group base on the name supplied.
	 * @param groupName Name of the group.
	 * @return Instance of the class <code>Group</code>
	 * @throws LDAPFindException If nothing is found.
	 */
	Group getGroup(final String groupName) throws LDAPFindException;	
}
