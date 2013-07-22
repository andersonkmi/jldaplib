package org.sharpsw.ldap.services;

import java.util.HashMap;
import java.util.List;

/**
 * This interface defines the methods for retrieving configuration information of the 
 * LDAP server.
 * @author Anderson Ito
 *
 */
public interface ILDAPServerInfoService extends ILDAPBaseService {
	
	/**
	 * Returns a hash map containing the server attributes.
	 * @return Hash map containing the server attributes.
	 */
	HashMap<String, List<String>> getServerAttributes();
}
