package org.codecraftlabs.ldap.services;

import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.validation.LDAPPolicyVerifier;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.Hashtable;

/**
 * LDAPConnectionFactory.java
 * This class is responsible for creating connections to
 * the LDAP server configured in the application.
 */
public class LDAPConnectionFactory {

	private static final String SSL_CONNECTION = "ssl";
	private static final String TLS_CONNECTION = "tls";
	private LDAPPolicyVerifier policyVerifier;
	
	public LDAPConnectionFactory(LDAPPolicyVerifier policyVerifier) {
		this.policyVerifier = policyVerifier;
	}
		
	public final LDAPConnection getConnection(final LDAPResource resource) throws LDAPException {
		this.policyVerifier.verify(resource);

		LDAPConnection connection;
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
