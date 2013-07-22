package org.sharpsw.ldap.services.apacheds;

import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.ILDAPAuthenticationService;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPConnectionFactory;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.exception.LDAPFindException;

/**
 * Implementation of the <code>ILDAPAuthenticationService</code> interface for the Apache Directory Server.
 * @author Anderson Ito
 *
 */
public final class AuthenticationServiceImpl extends BaseService implements ILDAPAuthenticationService  {	
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
	 * @throws InvalidLoginOrPasswordException If an error occurs
	 */
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

	/**
	 * Authenticates an user.
	 * @param uid User id to authenticate.
	 * @param password User's password.
	 * @throws InvalidLoginOrPasswordException If the credentials are not correct.
	 */
	@Override
	public void authenticate(String uid, String password) throws InvalidLoginOrPasswordLDAPException {
		authenticate(uid, password, this.getLDAPResource().getUserBaseDN());
	}

}
