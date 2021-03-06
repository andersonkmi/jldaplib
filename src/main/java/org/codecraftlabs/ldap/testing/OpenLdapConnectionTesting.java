package org.codecraftlabs.ldap.testing;

import java.util.HashMap;

import org.codecraftlabs.ldap.services.ILDAPRetrievalService;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.services.LDAPRetrievalServiceFactory;
import org.codecraftlabs.ldap.services.LDAPServiceProviderType;
import org.codecraftlabs.ldap.User;

public class OpenLdapConnectionTesting {

	public static void main(String[] args) {
		try {
			//System.setProperty("javax.net.ssl.trustStore", "cacerts");
	    	//System.setProperty("javax.net.ssl.trustStorePassword", "secret");
			HashMap<String, String> connectionProperties = new HashMap<>();
			connectionProperties.put(LDAPResource.SERVER, "centos");
			connectionProperties.put(LDAPResource.PORT, "389");
			connectionProperties.put(LDAPResource.CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			connectionProperties.put(LDAPResource.BIND_USER, "cn=administrator,dc=sharpsw,dc=org");
			connectionProperties.put(LDAPResource.PASSWORD, "password");
			connectionProperties.put(LDAPResource.SEARCH_SCOPE, "subtree");
			connectionProperties.put(LDAPResource.SEARCH_TIMEOUT, "120");
			connectionProperties.put(LDAPResource.SECURITY_AUTH, "simple");
			connectionProperties.put(LDAPResource.ENCRYPTION, "none");
			connectionProperties.put(LDAPResource.SERVER_VENDOR, LDAPServiceProviderType.OPEN_LDAP_SERVICE_PROVIDER.name());
			connectionProperties.put(LDAPResource.USER_BASE_DN, "ou=users,dc=sharpsw,dc=org");
			connectionProperties.put(LDAPResource.GROUP_BASE_DN, "ou=groups,dc=sharpsw,dc=org");
			LDAPRetrievalServiceFactory factory = new LDAPRetrievalServiceFactory(connectionProperties);
			
			ILDAPRetrievalService service = factory.buildService();
			
			User user = service.getUser("user001");
			System.out.println(user.getDisplayName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
