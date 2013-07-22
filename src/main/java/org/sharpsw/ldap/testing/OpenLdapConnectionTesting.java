package org.sharpsw.ldap.testing;

import java.util.HashMap;

import org.sharpsw.ldap.User;
import org.sharpsw.ldap.services.ILDAPRetrievalService;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.LDAPRetrievalServiceFactory;
import org.sharpsw.ldap.services.LDAPServiceProviderType;

public class OpenLdapConnectionTesting {

	public static void main(String[] args) {
		try {
			//System.setProperty("javax.net.ssl.trustStore", "cacerts");
	    	//System.setProperty("javax.net.ssl.trustStorePassword", "secret");
			HashMap<String, String> connectionProperties = new HashMap<String, String>();
			connectionProperties.put(LDAPResource.SERVER, "centos");
			connectionProperties.put(LDAPResource.PORT, "389");
			connectionProperties.put(LDAPResource.CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			connectionProperties.put(LDAPResource.BIND_USER, "cn=administrator,dc=sharpsw,dc=org");
			connectionProperties.put(LDAPResource.PASSWORD, "Skynet001");
			connectionProperties.put(LDAPResource.SEARCH_SCOPE, "subtree");
			connectionProperties.put(LDAPResource.SEARCH_TIMEOUT, "120");
			connectionProperties.put(LDAPResource.SECURITY_AUTH, "simple");
			connectionProperties.put(LDAPResource.ENCRYPTION, "none");
			connectionProperties.put(LDAPResource.SERVER_VENDOR, LDAPServiceProviderType.OPEN_LDAP_SERVICE_PROVIDER.name());
			connectionProperties.put(LDAPResource.USER_BASE_DN, "ou=users,dc=sharpsw,dc=org");
			connectionProperties.put(LDAPResource.GROUP_BASE_DN, "ou=groups,dc=sharpsw,dc=org");
			LDAPRetrievalServiceFactory factory = new LDAPRetrievalServiceFactory(connectionProperties);
			
			ILDAPRetrievalService service = factory.buildService();
			
			User user = service.getUser("andersonkmi");
			System.out.println(user.getDisplayName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
