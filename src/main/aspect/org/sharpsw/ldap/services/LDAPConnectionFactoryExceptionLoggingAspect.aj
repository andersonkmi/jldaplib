package org.sharpsw.ldap.services;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPConnectionFactory;
import org.sharpsw.ldap.services.LDAPResource;
import org.apache.log4j.Logger;

public aspect LDAPConnectionFactoryExceptionLoggingAspect {
	private static final Logger logger = Logger.getLogger(LDAPConnectionFactory.class);
	
	pointcut getConnectionCall(LDAPResource resource) : execution(public * LDAPConnectionFactory.getConnection(LDAPResource)) && args(resource);
	
	after(LDAPResource resource) throwing(LDAPException exception) : getConnectionCall(resource) {
		StringBuffer log = new StringBuffer();
		log.append("General LDAP exception raised when obtaining a new connection: ").append(exception.getMessage());
		logger.error(log.toString(), exception);
	}
}
