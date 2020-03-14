package org.codecraftlabs.ldap.testing;

import java.util.HashMap;

import org.codecraftlabs.ldap.exception.LDAPServiceCreationException;
import org.codecraftlabs.ldap.exception.LDAPUserRemovalException;
import org.codecraftlabs.ldap.exception.ServiceFactoryInitException;
import org.codecraftlabs.ldap.services.ILDAPAdminService;
import org.codecraftlabs.ldap.services.LDAPAdminServiceProviderFactory;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.services.LDAPServiceProviderType;

public class ApacheDSTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HashMap<String, String> connectionProperties = new HashMap<String, String>();
			connectionProperties.put(LDAPResource.SERVER, "dash7");
			connectionProperties.put(LDAPResource.PORT, "10389");
			connectionProperties.put(LDAPResource.CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			connectionProperties.put(LDAPResource.BIND_USER, "uid=admin,ou=system");
			connectionProperties.put(LDAPResource.PASSWORD, "secret");
			connectionProperties.put(LDAPResource.SEARCH_SCOPE, "subtree");
			connectionProperties.put(LDAPResource.SEARCH_TIMEOUT, "120");
			connectionProperties.put(LDAPResource.SECURITY_AUTH, "simple");
			connectionProperties.put(LDAPResource.ENCRYPTION, "none");
			connectionProperties.put(LDAPResource.SERVER_VENDOR, LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.name());
			connectionProperties.put(LDAPResource.USER_BASE_DN, "ou=users,ou=system");
			connectionProperties.put(LDAPResource.GROUP_BASE_DN, "ou=groups,ou=system");
			LDAPAdminServiceProviderFactory factory = new LDAPAdminServiceProviderFactory(connectionProperties);
			ILDAPAdminService service = factory.buildService();
			//User user = new User("user", "User 1", "uid=user,ou=users,ou=system");
			//service.changePassword(user, "12345678");
			
			User teste = new User("teste", "Teste", "uid=teste,ou=users,ou=system", "Teste");
			//service.add(teste);
			service.remove(teste);
			service.terminate();
		} catch (LDAPServiceCreationException e) {
			e.printStackTrace();
		} catch (ServiceFactoryInitException e) {
			e.printStackTrace();
		} catch (LDAPUserRemovalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
