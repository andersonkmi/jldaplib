package org.sharpsw.ldap.services.openldap;

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
import org.sharpsw.ldap.util.DistinguishedNameBuilder;

public class AdminServiceImpl extends BaseService implements ILDAPAdminService {

	public AdminServiceImpl(final LDAPConnection connection, final LDAPResource resource, final DistinguishedNameBuilder builder) {
		super(connection, resource, builder);
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
			attributes.put("userPassword", "");
			
			this.getLDAPConnection().add(user.getDn(), attributes);
		} catch (NamingException exception) {
			throw new LDAPUserAddException("Error when adding a new user", exception);
		}
	}

	@Override
	public void remove(User user) throws LDAPUserRemovalException {
		try {
			this.getLDAPConnection().remove(user.getDn());
		} catch (NamingException exception) {
			throw new LDAPUserRemovalException("Exception when removing user", exception);
		}
	}

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
