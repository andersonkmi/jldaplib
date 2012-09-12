package org.sharpsw.ldap.services.apacheds;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPUserAddException;
import org.sharpsw.ldap.exception.LDAPUserPasswordChangeException;
import org.sharpsw.ldap.exception.LDAPUserRemovalException;
import org.sharpsw.ldap.services.ILDAPAdminService;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPResource;

/**
 * Implementation of the <code>ILDAPAdminService</code> for the Apache Directory Server. 
 * @author Anderson Ito
 *
 */
public class AdminServiceImpl extends BaseService implements ILDAPAdminService {

	/**
	 * Class constructor that accepts the LDAP connection object and the resoure object.
	 * @param connection Instance of the class <code>LDAPConnection</code>.
	 * @param resource Instance of the class <code>LDAPResource</code>.
	 */
	public AdminServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
		super(connection, resource);
	}
	
	/**
	 * Closes the connection with the server.
	 */
	@Override
	public void terminate() {
		try {
			this.getLDAPConnection().unbind();
		} catch (LDAPException exception) {
			// No action performed
		}
	}

	/**
	 * Adds a new user into the server.
	 * @param user Instance of the class <code>User</code> to be added into the server.
	 * @throws LDAPUserAddException If an error occurs during the operation.
	 */
	@Override
	public void add(User user) throws LDAPUserAddException {
		try {
			Attributes attributes = new BasicAttributes();
			Attribute objectClass = new BasicAttribute("objectClass");
			objectClass.add("inetOrgPerson");
			objectClass.add("organizationalPerson");
			objectClass.add("person");
			objectClass.add("top");
			attributes.put(objectClass);

			Attribute sn = new BasicAttribute("sn");
			Attribute cn = new BasicAttribute("cn");

			sn.add(user.getDisplayName());
			cn.add(user.getCommonName());

			attributes.put(sn);
			attributes.put(cn);
			attributes.put(UID, user.getId());
			attributes.put(DN, user.getDn());
			attributes.put("userPassword", "123456");
			
			this.getLDAPConnection().add(user.getDn(), attributes);
		} catch (NamingException exception) {
			throw new LDAPUserAddException("Error when adding a new user", exception);
		}
	}

	/**
	 * Removes an user from the server.
	 * @throws LDAPUserRemovalException If an error occurs when removing an user.
	 */
	@Override
	public void remove(User user) throws LDAPUserRemovalException {
		try {
			this.getLDAPConnection().remove(user.getDn());
		} catch (NamingException exception) {
			throw new LDAPUserRemovalException("Error when removing user", exception);
		}
	}

	/**
	 * Changes the password of an user.
	 * @param user Instance of the class <code>User</code> that will have its password changed.
	 * @param password String containing the new password.
	 * @throws LDAPUserPasswordChangeException If an error occurs during the process.
	 */
	@Override
	public void changePassword(User user, String password) throws LDAPUserPasswordChangeException {
		try {
			ModificationItem[] modifications = new ModificationItem[1];
			Attribute userPassword = new BasicAttribute("userPassword", password);			
			modifications[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, userPassword);			
			this.getLDAPConnection().modifyAttribute(modifications, user.getDn());			
		} catch (NamingException exception) {
			throw new LDAPUserPasswordChangeException("Error when changing password", exception);
		}
	}

}
