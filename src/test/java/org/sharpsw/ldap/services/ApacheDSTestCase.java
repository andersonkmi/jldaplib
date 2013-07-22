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
package org.sharpsw.ldap.services;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sharpsw.ldap.Group;
import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.sharpsw.ldap.services.exception.LDAPFindException;

public class ApacheDSTestCase {
    private ILDAPServerInfoService serverService;
    private ILDAPAuthenticationService authService;
    private ILDAPRetrievalService retrievalService;

    @Before
    public final void setUp() throws Exception {
    	//System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\andersonkmi\\Documents\\desenvolvimento\\sf-workspace\\jldaplib\\conf\\trusted.ks");
    	//System.setProperty("javax.net.ssl.trustStorePassword", "secret");
    	//System.setProperty("javax.net.debug", "ssl");
    	HashMap<String, String> connectionProperties = new HashMap<String, String>();
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

	@After
	public final void tearDown() throws Exception {
		this.serverService.terminate();
		this.authService.terminate();
		this.retrievalService.terminate();
	}

	@Test
	public final void testAuthenticateOK() throws InvalidLoginOrPasswordLDAPException {
		this.authService.authenticate("andersonkmi", "Skynet001", "ou=users,ou=system");
	}

	@Test(expected = InvalidLoginOrPasswordLDAPException.class)
	public final void testAuthenticateFailInvalidLogin() throws InvalidLoginOrPasswordLDAPException {
		this.authService.authenticate("teste", "teste", "ou=users,ou=system");
	}

	@Test(expected = InvalidLoginOrPasswordLDAPException.class)
	public final void testAuthenticateFailInvalidPassword() throws InvalidLoginOrPasswordLDAPException {
		this.authService.authenticate("andersonkmi", "teste", "ou=users,ou=system");
	}

	@Test
	public final void testRetrieveSingleUserOK() throws LDAPFindException {
		User user = this.retrievalService.getUser("andersonkmi", "ou=users,ou=system");
		Assert.assertEquals("andersonkmi", user.getId());
		Assert.assertNull(user.getDisplayName());
		Assert.assertEquals("uid=andersonkmi,ou=users,ou=system", user.getDn());
	}

	@Test(expected = LDAPFindException.class)
	public final void testRetrieveSingleUserFail() throws LDAPFindException {
		this.retrievalService.getUser("anderson", "ou=users,ou=system");
	}

	@Test
	public final void testRetrieveAllUsersOK() throws LDAPFindException {
		List<User> results = this.retrievalService.getAllUsers("ou=system");
		Assert.assertEquals(2, results.size());

		User andersonkmi = new User("andersonkmi", "Anderson", "uid=andersonkmi,ou=users,ou=system", "Anderson Ito");
		User admin = new User("admin", "admin", "uid=admin,ou=system", "Anderson Ito");
		if (!results.contains(andersonkmi)) {
			Assert.fail("Did not find user andersonkmi");
		}

		if (!results.contains(admin)) {
			Assert.fail("Did not find user admin");
		}
	}

	@Test(expected = LDAPFindException.class)
	public final void testRetrieveAllUsersFail() throws LDAPFindException {
		this.retrievalService.getAllUsers("ou=teste");
	}

	@Test
	public final void testRetrieveGroupOK() throws LDAPFindException {
		Group group = this.retrievalService.getGroup("sweng", "ou=groups,ou=system");
		Assert.assertNotNull(group);
		Assert.assertEquals("sweng", group.getId());
		Assert.assertEquals("cn=sweng,ou=groups,ou=system", group.getDn());

		List<String> members = group.getMembers();
		Assert.assertEquals(1, members.size());

		if (!members.contains("uid=andersonkmi,ou=users,ou=system")) {
			Assert.fail("Missing members in group");
		}
	}

	@Test
	public final void testRetrieveAllGroups() throws LDAPFindException {
		List<Group> groups = this.retrievalService.getGroups("ou=system");

		Assert.assertEquals(2, groups.size());

		Group sweng = new Group("sweng", "cn=sweng,ou=groups,ou=system");
		Group administrators = new Group("Administrators", "cn=Administrators,ou=groups,ou=system");

		if (!groups.contains(sweng)) {
			Assert.fail("Group 'sweng' not present");
		}

		if (!groups.contains(administrators)) {
			Assert.fail("Group 'administrators' not present");
		}
	}
	
	@Test
	public final void testGetServerAttributesOk() {
		HashMap<String, List<String>> elements = this.serverService.getServerAttributes();
		Assert.assertNotNull(elements);
		Assert.assertEquals(4, elements.size());
		Set<String> keys = elements.keySet();
		for(String key : keys) {
			System.out.println(key);
		}
	}
}
