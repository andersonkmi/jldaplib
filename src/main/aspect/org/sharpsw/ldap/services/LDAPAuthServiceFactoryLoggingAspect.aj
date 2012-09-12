package org.sharpsw.ldap.services;

import org.apache.log4j.Logger;

public aspect LDAPAuthServiceFactoryLoggingAspect {
	private static final Logger logger = Logger.getLogger(LDAPAuthServiceProviderFactory.class);
	
	pointcut buildService() : execution(public ILDAPAuthenticationService LDAPAuthServiceProviderFactory.buildService());
	
	before() : buildService() {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering LDAPAuthServiceProviderFactory.buildService()");
		}
	}
	
	after() : buildService() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving LDAPAuthServiceProviderFactory.buildService()");
		}
	}
}
