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
		checkSearchScope(resource.getSearchScope());
		checkSearchTimeout(resource.getSearchTimeout());
		checkSecurityAuthentication(resource.getSecurityAuthentication());
		checkServerVendor(resource.getServerVendor());
		checkUserBaseDN(resource.getUserBaseDN());
		checkGroupBaseDN(resource.getGroupBaseDN());
		return proceed(resource);
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
