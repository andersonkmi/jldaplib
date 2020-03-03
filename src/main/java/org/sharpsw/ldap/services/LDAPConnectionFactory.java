package org.sharpsw.ldap.services;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import javax.net.ssl.SSLSocketFactory;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.InvalidBindPasswordException;
import org.sharpsw.ldap.exception.InvalidBindUserException;
import org.sharpsw.ldap.exception.InvalidEncryptionMethodException;
import org.sharpsw.ldap.exception.InvalidGroupBaseDNException;
import org.sharpsw.ldap.exception.InvalidInitialContextFactoryException;
import org.sharpsw.ldap.exception.InvalidPortException;
import org.sharpsw.ldap.exception.InvalidResourceException;
import org.sharpsw.ldap.exception.InvalidSearchScopeException;
import org.sharpsw.ldap.exception.InvalidSearchTimeoutException;
import org.sharpsw.ldap.exception.InvalidSecurityAuthenticationException;
import org.sharpsw.ldap.exception.InvalidServerException;
import org.sharpsw.ldap.exception.InvalidServerVendorException;
import org.sharpsw.ldap.exception.InvalidUserBaseDNException;

/**
 * LDAPConnectionFactory.java
 * This class is responsible for creating connections to
 * the LDAP server configured in the application.
 */
public class LDAPConnectionFactory {

	private static final String SSL_CONNECTION = "ssl";
	private static final String TLS_CONNECTION = "tls";
	
	public LDAPConnectionFactory() {
	}
		
	public final LDAPConnection getConnection(final LDAPResource resource) throws LDAPException, 
	                                                                  InvalidBindPasswordException, 
	                                                                  InvalidBindUserException,
	                                                                  InvalidGroupBaseDNException, 
	                                                                  InvalidInitialContextFactoryException, 
	                                                                  InvalidPortException, 
	                                                                  InvalidResourceException,
	                                                                  InvalidSearchScopeException,
	                                                                  InvalidSearchTimeoutException,
	                                                                  InvalidSecurityAuthenticationException,
	                                                                  InvalidServerException,
	                                                                  InvalidServerVendorException,
	                                                                  InvalidEncryptionMethodException,
	                                                                  InvalidUserBaseDNException {		
		LDAPConnection connection = null;
		try {			
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, resource.getInitialContextFactory());
			env.put(Context.PROVIDER_URL, this.getURL(resource));
			env.put(Context.SECURITY_AUTHENTICATION, resource.getSecurityAuthentication());
			env.put(Context.SECURITY_PRINCIPAL, resource.getBindUser());
			env.put(Context.SECURITY_CREDENTIALS, resource.getPassword());
			if(SSL_CONNECTION.equals(resource.getEncryption())) {
				env.put(Context.SECURITY_PROTOCOL, SSL_CONNECTION);
			}
			
			LdapContext ctx = new InitialLdapContext(env, null);
			
			// The code below is not quite correct yet.
			if(TLS_CONNECTION.equals(resource.getEncryption())) {
				StartTlsResponse response = (StartTlsResponse) ctx.extendedOperation(new StartTlsRequest());
				response.setHostnameVerifier(new TLSHostnameVerifies());
				response.negotiate((SSLSocketFactory)SSLSocketFactory.getDefault());							
			}
			connection = new LDAPConnection(ctx);
			
			// Grabs the connection properties
			String scope = resource.getSearchScope();
			SearchScope searchScope;
			
			if (scope.equals("subtree")) {
				searchScope = SearchScope.SUBTREE;
			} else if (scope.equals("object")) {
				searchScope = SearchScope.OBJECT;
			} else if (scope.equals("onelevel")) {
				searchScope = SearchScope.ONE_LEVEL;
			} else {
				searchScope = SearchScope.NONE;
			}

			int searchTimeout = Integer.parseInt(resource.getSearchTimeout());
			String secAuth = resource.getSecurityAuthentication();
			
			connection.setSearchScope(searchScope);
			connection.setSearchTimeout(searchTimeout);
			connection.setSecurityAuthentication(secAuth);
		} catch (NamingException namingExc) {
			throw new LDAPException("Error during connection establishment", namingExc);
		} catch (IOException ioExc) {
			throw new LDAPException("Error during SSL/TLS connection establishment", ioExc);
		}
		return connection;
	}
	
	
	final String getURL(final LDAPResource resource) {
		String url = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("ldap://");
		buffer.append(resource.getServer());
		buffer.append(":");
		buffer.append(resource.getPort());
		url = buffer.toString();
		return url;
	}
}
