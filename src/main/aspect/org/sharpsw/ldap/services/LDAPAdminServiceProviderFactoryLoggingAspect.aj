package org.sharpsw.ldap.services;

import org.apache.log4j.Logger;

public aspect LDAPAdminServiceProviderFactoryLoggingAspect {
	private static final Logger logger = Logger.getLogger(LDAPAdminServiceProviderFactory.class);
	
	pointcut buildService() : execution(public ILDAPAdminService LDAPAdminServiceProviderFactory.buildService());
	
	before() : buildService() {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering LDAPAdminServiceProviderFactory.buildService()");
		}
	}
	
	after() : buildService() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving LDAPAdminServiceProviderFactory.buildService()");
		}
	}
}
