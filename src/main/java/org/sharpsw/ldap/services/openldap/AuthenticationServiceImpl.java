package org.sharpsw.ldap.services.openldap;

import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.ILDAPAuthenticationService;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPConnectionFactory;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.exception.LDAPFindException;
import org.sharpsw.ldap.util.DistinguishedNameBuilder;

public class AuthenticationServiceImpl extends BaseService implements ILDAPAuthenticationService {

	private RetrievalServiceImpl service;
	
	public AuthenticationServiceImpl(final LDAPConnection connection, final LDAPResource resource, DistinguishedNameBuilder builder) {
		super(connection, resource, builder);
		this.service = new RetrievalServiceImpl(connection, resource, builder);
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
	public void authenticate(String uid, String password, String baseDN) throws InvalidLoginOrPasswordLDAPException {
		try {			
			User user = this.service.getUser(uid, baseDN);
			LDAPResource localResource = this.getLDAPResource().clone();
			localResource.setProperty(LDAPResource.BIND_USER, user.getDn());
			localResource.setProperty(LDAPResource.PASSWORD, password);

			LDAPConnectionFactory factory = new LDAPConnectionFactory();
			LDAPConnection conn = factory.getConnection(localResource);
			conn.unbind();
		} catch (LDAPFindException exception) {
			throw new InvalidLoginOrPasswordLDAPException(exception.getMessage());
		} catch (LDAPException exception) {
			throw new InvalidLoginOrPasswordLDAPException(exception.getMessage(), exception);
		} catch (CloneNotSupportedException exception) {
			throw new InvalidLoginOrPasswordLDAPException(exception.getMessage(), exception);
		}
	}

	@Override
	public void authenticate(String uid, String password) throws InvalidLoginOrPasswordLDAPException {
		authenticate(uid, password, this.getLDAPResource().getUserBaseDN());
	}

}
