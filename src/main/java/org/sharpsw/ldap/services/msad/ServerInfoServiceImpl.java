package org.sharpsw.ldap.services.msad;

import java.util.HashMap;
import java.util.List;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.ILDAPServerInfoService;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPResource;

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
