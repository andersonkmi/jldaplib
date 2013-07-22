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

import org.junit.Test;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.InvalidBindPasswordException;
import org.sharpsw.ldap.exception.InvalidBindUserException;
import org.sharpsw.ldap.exception.InvalidGroupBaseDNException;
import org.sharpsw.ldap.exception.InvalidInitialContextFactoryException;
import org.sharpsw.ldap.exception.InvalidPortException;
import org.sharpsw.ldap.exception.InvalidResourceException;
import org.sharpsw.ldap.exception.InvalidEncryptionMethodException;
import org.sharpsw.ldap.exception.InvalidSearchScopeException;
import org.sharpsw.ldap.exception.InvalidSearchTimeoutException;
import org.sharpsw.ldap.exception.InvalidSecurityAuthenticationException;
import org.sharpsw.ldap.exception.InvalidServerException;
import org.sharpsw.ldap.exception.InvalidServerVendorException;
import org.sharpsw.ldap.exception.InvalidUserBaseDNException;
import org.sharpsw.ldap.services.LDAPConnectionFactory;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.LDAPServiceProviderType;

public class LDAPConnectionFactoryValidatorTestCase {

	@Test(expected = InvalidBindUserException.class)
	public final void testConnFactoryNullBindUserFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, null);
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "909");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "teste");
		resource.setProperty(LDAPResource.SERVER_VENDOR, LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidBindUserException.class)
	public final void testConnFactoryEmptyBindUserFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "909");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "teste");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidServerException.class)
	public final void testConnFactoryNullServerFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "909");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, null);
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidServerException.class)
	public final void testConnFactoryEmptyServerFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "909");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidPortException.class)
	public final void testConnFactoryNullPortFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, null);
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidPortException.class)
	public final void testConnFactoryEmptyPortFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidPortException.class)
	public final void testConnFactoryInvalidPortNumberFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "0");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidEncryptionMethodException.class)
	public final void testConnFactoryNullSSLFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, null);
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidEncryptionMethodException.class)
	public final void testConnFactoryEmptySSLFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidEncryptionMethodException.class)
	public final void testConnFactoryInvalidSSLFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "TESTE");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidBindPasswordException.class)
	public final void testConnFactoryNullBindPasswordFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, null);
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidBindPasswordException.class)
	public final void testConnFactoryEmptyBindPasswordFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidInitialContextFactoryException.class)
	public final void testConnFactoryNullInitialContextFactoryFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, null);
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidInitialContextFactoryException.class)
	public final void testConnFactoryEmptyInitialContextFactoryFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSearchScopeException.class)
	public final void testConnFactoryNullSearchScopeFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, null);
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSearchScopeException.class)
	public final void testConnFactoryEmptySearchScopeFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSearchScopeException.class)
	public final void testConnFactoryInvalidSearchScopeFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "teste");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSearchTimeoutException.class)
	public final void testConnFactoryNullSearchTimeoutFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, null);
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSearchTimeoutException.class)
	public final void testConnFactoryEmptySearchTimeoutFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSecurityAuthenticationException.class)
	public final void testConnFactoryNullSecurityAuthenticationFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, null);
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidSecurityAuthenticationException.class)
	public final void testConnFactoryEmptySecurityAuthenticationFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidServerVendorException.class)
	public final void testConnFactoryNullServerVendorFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR, null);
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidServerVendorException.class)
	public final void testConnFactoryEmptyServerVendorFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR, "");
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidServerVendorException.class)
	public final void testConnFactoryInvalidServerVendorFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR, "teste");
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidUserBaseDNException.class)
	public final void testConnFactoryNullUserBaseDNFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, null);

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidUserBaseDNException.class)
	public final void testConnFactoryEmptyUserBaseDNFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "teste");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidGroupBaseDNException.class)
	public final void testConnFactoryNullGroupBaseDNFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, null);
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}

	@Test(expected = InvalidGroupBaseDNException.class)
	public final void testConnFactoryEmptyGroupBaseDNFail()
			throws InvalidBindPasswordException, InvalidBindUserException,
			InvalidGroupBaseDNException, InvalidInitialContextFactoryException,
			InvalidPortException, InvalidResourceException,
			InvalidSearchScopeException, InvalidSearchTimeoutException,
			InvalidSecurityAuthenticationException, InvalidServerException,
			InvalidServerVendorException, InvalidEncryptionMethodException,
			InvalidUserBaseDNException, LDAPException {
		LDAPResource resource = new LDAPResource();
		resource.setProperty(LDAPResource.BIND_USER, "teste");
		resource.setProperty(LDAPResource.CONTEXT_FACTORY, "teste");
		resource.setProperty(LDAPResource.GROUP_BASE_DN, "");
		resource.setProperty(LDAPResource.PASSWORD, "teste");
		resource.setProperty(LDAPResource.PORT, "389");
		resource.setProperty(LDAPResource.SEARCH_SCOPE, "subtree");
		resource.setProperty(LDAPResource.SEARCH_TIMEOUT, "100");
		resource.setProperty(LDAPResource.SECURITY_AUTH, "simple");
		resource.setProperty(LDAPResource.SERVER, "server");
		resource.setProperty(LDAPResource.SERVER_VENDOR,
				LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString());
		resource.setProperty(LDAPResource.ENCRYPTION, "none");
		resource.setProperty(LDAPResource.USER_BASE_DN, "teste");

		LDAPConnectionFactory factory = new LDAPConnectionFactory();
		factory.getConnection(resource);
	}
}
