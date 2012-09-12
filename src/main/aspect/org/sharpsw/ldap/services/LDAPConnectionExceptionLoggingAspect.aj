package org.sharpsw.ldap.services;

import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.sharpsw.ldap.exception.LDAPLookupException;
import org.sharpsw.ldap.services.LDAPConnection;

public aspect LDAPConnectionExceptionLoggingAspect {
	private static final Logger logger = Logger.getLogger(LDAPConnection.class);
	
	pointcut bindCall(String uid, String password) : execution(public void LDAPConnection.bind(String, String)) && args(uid, password);
	pointcut unbindCall() : execution(public void LDAPConnection.unbind());
	pointcut lookupUserCall(String baseDN, String filter, List<String> attrs) : execution(public NamingEnumeration<SearchResult> LDAPConnection.lookup(String, String, List<String>)) && args(baseDN, filter, attrs);
	
	after(String uid, String password) throwing(InvalidLoginOrPasswordLDAPException exception) : bindCall(uid, password) {
		StringBuffer log = new StringBuffer();
		log.append("Invalid login/password exception raised: ").append(exception.getMessage());
		logger.error(log.toString(), exception);
	}
	
	after(String baseDN, String filter, List<String> attrs) throwing(LDAPLookupException exception) : lookupUserCall(baseDN, filter, attrs) {
		StringBuffer log = new StringBuffer();
		log.append("Lookup exception raised when calling LDAPConnection.lookupUser() method.");
		logger.error(log.toString(), exception);
	}
	
	after() throwing(LDAPException exception) : unbindCall() {
		StringBuilder log = new StringBuilder();
		log.append("Error when calling unbind");
		logger.error(log.toString(), exception);
	}
}
