package org.sharpsw.ldap.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.sharpsw.ldap.Group;
import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.sharpsw.ldap.services.exception.LDAPFindException;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled
public class ApacheDSTestCase {
    private ILDAPServerInfoService serverService;
    private ILDAPAuthenticationService authService;
    private ILDAPRetrievalService retrievalService;

    @BeforeEach
    public final void setUp() throws Exception {
    	//System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\andersonkmi\\Documents\\desenvolvimento\\sf-workspace\\jldaplib\\conf\\trusted.ks");
    	//System.setProperty("javax.net.ssl.trustStorePassword", "secret");
    	//System.setProperty("javax.net.debug", "ssl");
    	HashMap<String, String> connectionProperties = new HashMap<>();
		connectionProperties.put(LDAPResource.SERVER, "localhost");
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
		
		LDAPServerInfoServiceProviderFactory factory = new LDAPServerInfoServiceProviderFactory(connectionProperties);
		this.serverService = factory.buildService();
		
		LDAPAuthServiceProviderFactory authFactory = new LDAPAuthServiceProviderFactory(connectionProperties);
		this.authService = authFactory.buildService();
		
		LDAPRetrievalServiceFactory retrievalFactory = new LDAPRetrievalServiceFactory(connectionProperties);
		this.retrievalService = retrievalFactory.buildService();
	}

	@AfterEach
	public final void tearDown() {
		this.serverService.terminate();
		this.authService.terminate();
		this.retrievalService.terminate();
	}

	@Test
	public final void testAuthenticateOK() throws InvalidLoginOrPasswordLDAPException {
		this.authService.authenticate("andersonkmi", "Skynet001", "ou=users,ou=system");
	}

	@Test
	public final void testAuthenticateFailInvalidLogin() {
		assertThrows(InvalidLoginOrPasswordLDAPException.class, () -> this.authService.authenticate("teste", "teste", "ou=users,ou=system"));
	}

	@Test
	public final void testAuthenticateFailInvalidPassword() {
		assertThrows(InvalidLoginOrPasswordLDAPException.class, () -> this.authService.authenticate("andersonkmi", "teste", "ou=users,ou=system"));
	}

	@Test
	public final void testRetrieveSingleUserOK() throws LDAPFindException {
		User user = this.retrievalService.getUser("andersonkmi", "ou=users,ou=system");
		assertEquals("andersonkmi", user.getId());
		assertNull(user.getDisplayName());
		assertEquals("uid=andersonkmi,ou=users,ou=system", user.getDn());
	}

	@Test
	public final void testRetrieveSingleUserFail() {
		assertThrows(LDAPFindException.class, () -> this.retrievalService.getUser("anderson", "ou=users,ou=system"));
	}

	@Test
	public final void testRetrieveAllUsersOK() throws LDAPFindException {
		List<User> results = this.retrievalService.getAllUsers("ou=system");
		assertEquals(2, results.size());

		User andersonkmi = new User("andersonkmi", "Anderson", "uid=andersonkmi,ou=users,ou=system", "Anderson Ito");
		User admin = new User("admin", "admin", "uid=admin,ou=system", "Anderson Ito");
		if (!results.contains(andersonkmi)) {
			fail("Did not find user andersonkmi");
		}

		if (!results.contains(admin)) {
			fail("Did not find user admin");
		}
	}

	@Test
	public final void testRetrieveAllUsersFail() {
		assertThrows(LDAPFindException.class, () -> this.retrievalService.getAllUsers("ou=teste"));
	}

	@Test
	public final void testRetrieveGroupOK() throws LDAPFindException {
		Group group = this.retrievalService.getGroup("sweng", "ou=groups,ou=system");
		assertNotNull(group);
		assertEquals("sweng", group.getId());
		assertEquals("cn=sweng,ou=groups,ou=system", group.getDn());

		List<String> members = group.getMembers();
		assertEquals(1, members.size());

		if (!members.contains("uid=andersonkmi,ou=users,ou=system")) {
			fail("Missing members in group");
		}
	}

	@Test
	public final void testRetrieveAllGroups() throws LDAPFindException {
		List<Group> groups = this.retrievalService.getGroups("ou=system");

		assertEquals(2, groups.size());

		Group sweng = new Group("sweng", "cn=sweng,ou=groups,ou=system");
		Group administrators = new Group("Administrators", "cn=Administrators,ou=groups,ou=system");

		if (!groups.contains(sweng)) {
			fail("Group 'sweng' not present");
		}

		if (!groups.contains(administrators)) {
			fail("Group 'administrators' not present");
		}
	}
	
	@Test
	public final void testGetServerAttributesOk() {
		HashMap<String, List<String>> elements = this.serverService.getServerAttributes();
		assertNotNull(elements);
		assertEquals(4, elements.size());
		Set<String> keys = elements.keySet();
		for(String key : keys) {
			System.out.println(key);
		}
	}
}
