package org.codecraftlabs.ldap.services.msad;

import org.codecraftlabs.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.services.ILDAPAuthenticationService;
import org.codecraftlabs.ldap.services.LDAPConnectionFactory;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.services.exception.LDAPFindException;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.services.LDAPConnection;
import org.codecraftlabs.ldap.validation.LDAPPolicyValidator;

import java.util.Collections;

public class AuthenticationServiceImpl extends BaseService implements ILDAPAuthenticationService {
	private RetrievalServiceImpl service;
	
	public AuthenticationServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
		super(connection, resource);
		this.service = new RetrievalServiceImpl(connection, resource);
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

			LDAPConnectionFactory factory = new LDAPConnectionFactory(new LDAPPolicyValidator(Collections.emptySet()));
			LDAPConnection conn = factory.getConnection(localResource);
			conn.unbind();
		} catch (LDAPFindException exception) {
			throw new InvalidLoginOrPasswordLDAPException(exception.getMessage());
		} catch (LDAPException | CloneNotSupportedException exception) {
			throw new InvalidLoginOrPasswordLDAPException(exception.getMessage(), exception);
		}
	}

	@Override
	public void authenticate(String uid, String password) throws InvalidLoginOrPasswordLDAPException {
		authenticate(uid, password, this.getLDAPResource().getUserBaseDN());
	}

}
