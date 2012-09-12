package org.sharpsw.ldap.services;

import org.sharpsw.ldap.exception.InvalidParameterException;
import java.util.HashMap;

public aspect ServiceProviderFactoryValidationAspect {
	pointcut baseServiceProviderFactoryCtor1(String resource) : execution(LDAPBaseServiceProviderFactory.new(String)) && args(resource);
	pointcut baseServiceProviderFactoryCtor2(LDAPResource resource) : execution(LDAPBaseServiceProviderFactory.new(LDAPResource)) && args(resource);
	pointcut baseServiceProviderFactoryCtor3(final HashMap<String, String> resource) : execution(LDAPBaseServiceProviderFactory.new(HashMap<String, String>)) && args(resource);
	
	before(String resource) throws InvalidParameterException : baseServiceProviderFactoryCtor1(resource) {
		if(resource == null) {
			StringBuilder buffer = new StringBuilder();
			buffer.append("The resource provided is null");
			throw new InvalidParameterException(buffer.toString());
		}
		
		if(resource.isEmpty()) {
			throw new InvalidParameterException("The resource name is empty");
		}
	}
	
	before(LDAPResource resource) throws InvalidParameterException : baseServiceProviderFactoryCtor2(resource) {
		if(resource == null) {
			throw new InvalidParameterException("The resource object is null");
		}
	}
	
	before(final HashMap<String, String> resource) throws InvalidParameterException : baseServiceProviderFactoryCtor3(resource) {
		if(resource == null) {
			throw new InvalidParameterException("The resource object is null");
		}
	}
}
