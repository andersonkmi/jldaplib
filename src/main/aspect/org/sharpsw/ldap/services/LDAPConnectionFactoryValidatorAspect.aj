package org.sharpsw.ldap.services;
import org.sharpsw.ldap.exception.InvalidBindUserException;
import org.sharpsw.ldap.exception.InvalidGroupBaseDNException;
import org.sharpsw.ldap.exception.InvalidInitialContextFactoryException;
import org.sharpsw.ldap.exception.InvalidBindPasswordException;
import org.sharpsw.ldap.exception.InvalidPortException;
import org.sharpsw.ldap.exception.InvalidResourceException;
import org.sharpsw.ldap.exception.InvalidEncryptionMethodException;
import org.sharpsw.ldap.exception.InvalidSearchScopeException;
import org.sharpsw.ldap.exception.InvalidSearchTimeoutException;
import org.sharpsw.ldap.exception.InvalidSecurityAuthenticationException;
import org.sharpsw.ldap.exception.InvalidServerException;
import org.sharpsw.ldap.exception.InvalidServerVendorException;
import org.sharpsw.ldap.exception.InvalidUserBaseDNException;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.LDAPServiceProviderType;

public aspect LDAPConnectionFactoryValidatorAspect {
	pointcut getConnectionCall(LDAPResource resource) : call (public LDAPConnection LDAPConnectionFactory.getConnection(LDAPResource)) && args(resource);
	
	LDAPConnection around(LDAPResource resource) throws InvalidBindUserException, 
	                                                    InvalidGroupBaseDNException, 
	                                                    InvalidInitialContextFactoryException, 
	                                                    InvalidBindPasswordException, 
	                                                    InvalidPortException, 
	                                                    InvalidResourceException, 
	                                                    InvalidSearchScopeException, 
	                                                    InvalidSearchTimeoutException, 
	                                                    InvalidSecurityAuthenticationException, 
	                                                    InvalidServerException, 
	                                                    InvalidServerVendorException, 
	                                                    InvalidEncryptionMethodException, 
	                                                    InvalidUserBaseDNException : getConnectionCall(resource) {
		if(resource == null) {
			StringBuffer message = new StringBuffer();
			message.append("Null LDAP resource detected when requesting a new connection.");
			throw new InvalidResourceException(message.toString());
		}
		checkBindUser(resource.getBindUser());
		checkServer(resource.getServer());
		checkPort(resource.getPort());
		checkEncryptionMethod(resource.getEncryption());		
		checkPassword(resource.getPassword());
		checkInitialContextFactory(resource.getInitialContextFactory());
		checkSearchScope(resource.getSearchScope());
		checkSearchTimeout(resource.getSearchTimeout());
		checkSecurityAuthentication(resource.getSecurityAuthentication());
		checkServerVendor(resource.getServerVendor());
		checkUserBaseDN(resource.getUserBaseDN());
		checkGroupBaseDN(resource.getGroupBaseDN());
		return proceed(resource);
	}
	
	void checkBindUser(String user) throws InvalidBindUserException {
		if(user == null) {
			throw new InvalidBindUserException("Null bind user supplied.");
		} else if(user.isEmpty()) {
			throw new InvalidBindUserException("Bind user name empty.");
		}
	}
	
	void checkServer(String server) throws InvalidServerException {
		if(server == null) {
			throw new InvalidServerException("Null server name supplied");
		} else if(server.isEmpty()) {
			throw new InvalidServerException("Server name empty");
		}
	}
	
	void checkPort(String port) throws InvalidPortException {
		if(port == null) {
			throw new InvalidPortException("Null port supplied.");
		} else if(port.isEmpty()) {
			throw new InvalidPortException("Empty port number supplied.");
		} else {
			try {
				int portNumber = Integer.parseInt(port);
				if(portNumber < 1) {
					StringBuffer message = new StringBuffer();
					message.append("Port number is invalid: ").append(portNumber);
					throw new InvalidPortException(message.toString());
				}
			} catch (NumberFormatException exception) {
				throw new InvalidPortException("Invalid number supplied", exception);
			}
		}
	}
	
	void checkEncryptionMethod(String flag) throws InvalidEncryptionMethodException {
		if(flag == null) {
			throw new InvalidEncryptionMethodException("Encryption information is null");
		} else if(flag.isEmpty()) {
			throw new InvalidEncryptionMethodException("Encryption information is empty");
		} else if(!flag.equals("none") && !flag.equals("ssl") && !flag.equals("tls")) {
			StringBuffer message = new StringBuffer();
			message.append("Encryption is invalid: ").append(flag);
			throw new InvalidEncryptionMethodException(message.toString());
		}
	}
	
	void checkPassword(String password) throws InvalidBindPasswordException {
		if(password == null) {
			throw new InvalidBindPasswordException("Null password supplied");
		} else if(password.isEmpty()) {
			throw new InvalidBindPasswordException("Password is empty");
		}
	}
	
	void checkInitialContextFactory(String context) throws InvalidInitialContextFactoryException {
		if(context == null) {
			throw new InvalidInitialContextFactoryException("Null initial context factory");
		} else if(context.isEmpty()) {
			throw new InvalidInitialContextFactoryException("Empty initial context factory");
		}
	}
	
	void checkSearchScope(String scope) throws InvalidSearchScopeException {
		if(scope == null) {
			throw new InvalidSearchScopeException("Null search scope");
		} else if(scope.isEmpty()) {
			throw new InvalidSearchScopeException("Empty scope.");
		}
		// Verifies the value
		if(!(scope.equalsIgnoreCase("object") || scope.equalsIgnoreCase("subtree") || scope.equalsIgnoreCase("onelevel"))) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Invalid search scope value '").append(scope).append("'");
			throw new InvalidSearchScopeException(buffer.toString());
		}
	}
	
	void checkSearchTimeout(String timeout) throws InvalidSearchTimeoutException {
		if(timeout == null) {
			throw new InvalidSearchTimeoutException("Null timeout");
		} else if(timeout.isEmpty()) {
			throw new InvalidSearchTimeoutException("Empty timeout information");
		}
	}
	
	void checkSecurityAuthentication(String auth) throws InvalidSecurityAuthenticationException {
		if(auth == null) {
			throw new InvalidSecurityAuthenticationException("Null security authentication");
		} else if(auth.isEmpty()) {
			throw new InvalidSecurityAuthenticationException("Empty security authentication");
		}
		
		// Checks the supplied value
		if(!auth.equals("simple") && !auth.equals("none")) {
			throw new InvalidSecurityAuthenticationException("The valid values are: simple or none");
		}
	}
	
	void checkServerVendor(String vendor) throws InvalidServerVendorException {
		if(vendor == null) {
			throw new InvalidServerVendorException("Null vendor name supplied");
		} else if(vendor.isEmpty()) {
			throw new InvalidServerVendorException("Empty vendor name supplied");
		}
		
		// check the value
		if(!(vendor.equals(LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.toString()) || 
			 vendor.equals(LDAPServiceProviderType.MS_ACTIVE_DIRECTORY_2003_SERVICE_PROVIDER.toString()) || 
		     vendor.equals(LDAPServiceProviderType.OPEN_LDAP_SERVICE_PROVIDER.toString()))) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Invalid vendor supplied '").append(vendor).append("'");
			throw new InvalidServerVendorException(buffer.toString());
		}
	}
	
	void checkUserBaseDN(String dn) throws InvalidUserBaseDNException {
		if(dn == null) {
			throw new InvalidUserBaseDNException("Null user base dn supplied");
		} else if(dn.isEmpty()) {
			throw new InvalidUserBaseDNException("Empty user base dn supplied");
		}
	}
	
	void checkGroupBaseDN(String dn) throws InvalidGroupBaseDNException {
		if(dn == null) {
			throw new InvalidGroupBaseDNException("Null group base dn supplied");
		} else if(dn.isEmpty()) {
			throw new InvalidGroupBaseDNException("Empty group base dn supplied");
		}		
	}
}
