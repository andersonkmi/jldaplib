package org.codecraftlabs.ldap.services.apacheds;

import org.codecraftlabs.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.services.ILDAPAuthenticationService;
import org.codecraftlabs.ldap.services.LDAPConnectionFactory;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.services.exception.LDAPFindException;
import org.codecraftlabs.ldap.validation.LDAPPolicyValidator;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.services.LDAPConnection;

import java.util.Collections;

/**
 * Implementation of the <code>ILDAPAuthenticationService</code> interface for the Apache Directory Server.
 * @author Anderson Ito
 *
 */
public final class AuthenticationServiceImpl extends BaseService implements ILDAPAuthenticationService {
	private RetrievalServiceImpl service;
	
	/**
	 * Class constructor that receives the connection with the ldap server and the resource information.
	 * @param connection Instance of the class <code>LDAPConnection</code> containing the underlying connection to the server.
	 * @param resource Resources object containing configuration information.
	 */
	public AuthenticationServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
		super(connection, resource);
		this.service = new RetrievalServiceImpl(connection, resource);
	}	

	/**
	 * Closes the connection to the server.
	 */
	@Override
	public final void terminate() {
		try {
			this.getLDAPConnection().unbind();
		} catch (LDAPException exception) {
			// No action performed
			// Logging later
		}

	}
	
	/**
	 * Sets the user id attribute to be used during object search.
	 * @param uidAttr String containing the user id attribute to be used.
	 */
	@Override
	public void setUserIdAttribute(String uidAttr) {
		if(this.service != null) {
			this.service.setUserIdentificationAttribute(uidAttr);
		}
	}

	/**
	 * Performs the authentication of an user.
	 * @param uid String containing the user identification.
	 * @param password String containing the password.
	 * @param baseDN Base DN to start the user search for authentication purposes.
	 */
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

	/**
	 * Authenticates an user.
	 * @param uid User id to authenticate.
	 * @param password User's password.
	 */
	@Override
	public void authenticate(String uid, String password) throws InvalidLoginOrPasswordLDAPException {
		authenticate(uid, password, this.getLDAPResource().getUserBaseDN());
	}

}
