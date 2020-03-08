package org.codecraftlabs.ldap.services.msad;

import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.exception.LDAPUserAddException;
import org.codecraftlabs.ldap.exception.LDAPUserPasswordChangeException;
import org.codecraftlabs.ldap.exception.LDAPUserRemovalException;
import org.codecraftlabs.ldap.services.ILDAPAdminService;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.services.LDAPConnection;

public class AdminServiceImpl extends BaseService implements ILDAPAdminService {

	public AdminServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
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
	public void add(User user) throws LDAPUserAddException {
	}

	@Override
	public void remove(User user) throws LDAPUserRemovalException {
	}

	@Override
	public void changePassword(User user, String password) throws LDAPUserPasswordChangeException {
	}

}
