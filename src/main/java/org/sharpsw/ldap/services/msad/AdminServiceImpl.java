package org.sharpsw.ldap.services.msad;

import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPUserAddException;
import org.sharpsw.ldap.exception.LDAPUserPasswordChangeException;
import org.sharpsw.ldap.exception.LDAPUserRemovalException;
import org.sharpsw.ldap.services.ILDAPAdminService;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPResource;

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
