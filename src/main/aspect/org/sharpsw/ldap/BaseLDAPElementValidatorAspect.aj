package org.sharpsw.ldap;

import org.sharpsw.ldap.exception.InvalidParameterException;

public aspect BaseLDAPElementValidatorAspect {
	pointcut idFieldAssignment(String id, BaseLDAPElement element) : set(private String BaseLDAPElement.id) && args(id) && target(element);
	pointcut distinguishedNameAssignment(String distinguishedName, BaseLDAPElement element) : set(private String BaseLDAPElement.dn) && args(distinguishedName) && target(element);
	pointcut commonNameAssignment(String commonName, BaseLDAPElement element) : set(private String BaseLDAPElement.commonName) && args(commonName) && target(element);	

	void around(String id, BaseLDAPElement element) throws InvalidParameterException : idFieldAssignment(id, element) {	
		if(id == null) {
			StringBuffer message = new StringBuffer();
			message.append("The id field is null");
			throw new InvalidParameterException(message.toString());
		} else if(id.isEmpty()) {
			StringBuffer message = new StringBuffer();
			message.append("The id field is empty");
			throw new InvalidParameterException(message.toString());
		} else {
			proceed(id, element);
		}
	}
	
	void around(String distinguishedName, BaseLDAPElement element) throws InvalidParameterException : distinguishedNameAssignment(distinguishedName, element) {
		if(distinguishedName == null) {
			StringBuffer message = new StringBuffer();
			message.append("The distinguished name is null");
			throw new InvalidParameterException(message.toString());
		} else if(distinguishedName.isEmpty()) {
			StringBuffer message = new StringBuffer();
			message.append("The distinguished name is empty");
			throw new InvalidParameterException(message.toString());
		} else {
			proceed(distinguishedName, element);
		}
	}
	
	void around(String commonName, BaseLDAPElement element) throws InvalidParameterException : commonNameAssignment(commonName, element) {
		if(commonName == null) {
			StringBuilder message = new StringBuilder();
			message.append("The common name object is null");
			throw new InvalidParameterException(message.toString());
		} else if(commonName.isEmpty()) {
			throw new InvalidParameterException("The common name is an empty string");
		} else {
			proceed(commonName, element);
		}
	}
}
