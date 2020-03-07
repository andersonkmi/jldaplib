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
		checkUserBaseDN(resource.getUserBaseDN());
		checkGroupBaseDN(resource.getGroupBaseDN());
		return proceed(resource);
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
