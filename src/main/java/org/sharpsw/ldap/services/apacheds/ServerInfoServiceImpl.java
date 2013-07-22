package org.sharpsw.ldap.services.apacheds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

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
		StringBuilder serverName = new StringBuilder();
		serverName.append("ldap://").append(this.getLDAPResource().getServer()).append(":").append(this.getLDAPResource().getPort());
		
		List<String> elements = new ArrayList<String>();
		elements.add("namingContexts");
		elements.add("supportedExtension");
		elements.add("supportedLDAPVersion");
		elements.add("vendorName");
		elements.add("vendorVersion");
		
		String server = serverName.toString();
		try {
			return this.getLDAPConnection().getServerAttributes(server, elements);	
		} catch (NamingException exception) {
			return null;
		}
	}

}
