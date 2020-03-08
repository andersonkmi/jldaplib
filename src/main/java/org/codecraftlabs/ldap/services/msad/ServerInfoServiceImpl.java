package org.codecraftlabs.ldap.services.msad;

import java.util.HashMap;
import java.util.List;

import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.services.ILDAPServerInfoService;
import org.codecraftlabs.ldap.services.LDAPConnection;

public class ServerInfoServiceImpl extends BaseService implements ILDAPServerInfoService {

	public ServerInfoServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
		super(connection, resource);
	}
	
	@Override
	public void terminate() {
		try {
			this.getLDAPConnection().unbind();
		} catch (LDAPException exception) {
			// No action performed		
		}
	}

	@Override
	public HashMap<String, List<String>> getServerAttributes() {
		return null;
	}

}
