package org.sharpsw.ldap.services;

/**
 * Base interface for other LDAP services to be defined.
 * @author Anderson Ito
 *
 */
public interface ILDAPBaseService {
	
	/**
	 * Closes the connection with the LDAP server.
	 */
	void terminate();
}
