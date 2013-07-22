package org.sharpsw.ldap.services;

import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;

/**
 * This interface defines the operations related to LDAP authentication.
 * @author Anderson Ito
 *
 */
public interface ILDAPAuthenticationService extends ILDAPBaseService {
	
	/**
	 * Authenticates a user by providing its user name, password and the base DN to start the LDAP search.
	 * @param uid String containing the user name.
	 * @param password String containing the password
	 * @param baseDN Base DN to start the user search.
	 * @throws InvalidLoginOrPasswordLDAPException If the authentication fails.
	 */
	void authenticate(final String uid, final String password, final String baseDN) throws InvalidLoginOrPasswordLDAPException;
	
	/**
	 * Authenticates an user by providing its user name and password.
	 * @param uid String containing the user name.
	 * @param password String containing the password.
	 * @throws InvalidLoginOrPasswordLDAPException If the authentication fails.
	 */
	void authenticate(String uid, String password) throws InvalidLoginOrPasswordLDAPException;
	
	/**
	 * Sets the user id attribute information.
	 * @param uidAttributeName String containing the user id attribute name.
	 */
	void setUserIdAttribute(String uidAttributeName);
}
