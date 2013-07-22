package org.sharpsw.ldap.services;

import org.apache.log4j.Logger;
import org.sharpsw.ldap.User;
import org.sharpsw.ldap.services.exception.LDAPFindException;

public aspect ILDAPRetrievalServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(ILDAPRetrievalService.class);
	
	pointcut getUserByUidAndDN(String uid, String dn) : execution(public User ILDAPRetrievalService+.getUser(String, String)) && args(uid, dn);
	
	before(String uid, String dn) : getUserByUidAndDN(uid, dn) {
		if(logger.isTraceEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Entering ILDAPRetrievalService.getUser(String uid, String dn) method.");
			logger.trace(log.toString());
		}
		
		// Debug logging
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding user using the following values: uid = '").append(uid).append("' and DN = '").append(dn).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String uid, String dn) : getUserByUidAndDN(uid, dn) {
		if(logger.isTraceEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Leaving ILDAPRetrievalService.getUser(String uid, String dn) method.");
			logger.trace(log.toString());
		}
	}
	
	after(String uid, String dn) throwing (LDAPFindException exception) : getUserByUidAndDN(uid, dn) {
		StringBuffer log = new StringBuffer();
		log.append("No entry was found. Message: '").append(exception.getMessage()).append("'.");
		logger.warn(log.toString(), exception);
	}
	
	after(String uid, String dn) returning (User user) : getUserByUidAndDN(uid, dn) {
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("The user '").append(user.toString()).append("' was retrieved from the LDAP server.");
			logger.debug(log.toString());			
		}
	}
}
