package org.sharpsw.ldap.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.sharpsw.ldap.exception.InvalidLoginOrPasswordLDAPException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPLookupException;

/**
 * This class wraps around a connection to the LDAP server.
 * @author Anderson Ito
 *
 */
public final class LDAPConnection {
	private LdapContext context;
	private int timeout = 0;
	private int searchScope = SearchControls.SUBTREE_SCOPE;
	private String securityAuthentication = "simple";

	@SuppressWarnings("unused")
	private LDAPConnection() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param ctx
	 *            DirContext instance.
	 */
	LDAPConnection(final LdapContext ctx) {
		this.context = ctx;
	}

	/**
	 * Binds a user into the connection using the password supplied.
	 * 
	 * @param user
	 *            User to bind
	 * @param password
	 *            User's password.
	 * @throws InvalidLoginOrPasswordLDAPException
	 *             if user/password is invalid.
	 */
	public void bind(final String user, final String password) throws InvalidLoginOrPasswordLDAPException {
		try {
			this.context.addToEnvironment(Context.SECURITY_AUTHENTICATION, this.securityAuthentication);
			this.context.addToEnvironment(Context.SECURITY_PRINCIPAL, user);
			this.context.addToEnvironment(Context.SECURITY_CREDENTIALS, password);		
		} catch (NamingException namingExc) {
			throw new InvalidLoginOrPasswordLDAPException(namingExc.getMessage(), namingExc);
		}
	}

	/**
	 * Closes the connection to the LDAP server.
	 * 
	 * @throws LDAPException
	 *             if a problem happens during the connection closing.
	 */
	public void unbind() throws LDAPException {
		try {
			this.context.close();
		} catch (NamingException namingExc) {
			throw new LDAPException("Exception caught during connection closing", namingExc);
		}
	}

	/**
	 * Sets the timeout information used during LDAP search operations.
	 * @param tmout Timeout in miliseconds.
	 */
	public void setSearchTimeout(final int tmout) {
		this.timeout = tmout;
	}

	/**
	 * Sets the search 
	 * @param scope
	 */
	public void setSearchScope(SearchScope scope) {
		switch(scope) {
		case OBJECT:
			this.searchScope = SearchControls.OBJECT_SCOPE;
			break;
		case ONE_LEVEL:
			this.searchScope = SearchControls.ONELEVEL_SCOPE;
			break;
		case SUBTREE:
			this.searchScope = SearchControls.SUBTREE_SCOPE;
			break;
		}
	}

	/**
	 * Sets the security authentication mode.
	 * @param auth String containing the authentication mode
	 */
	public void setSecurityAuthentication(final String auth) {
		this.securityAuthentication = auth;
	}

	/**
	 * Executes a search operation on the LDAP server.
	 * @param baseDN Base DN to start the search
	 * @param filter Filter information of the search
	 * @param attributes Attributes to be returned with the search results
	 * @return Set of the objects that match the search criteria passed to the method.
	 * @throws LDAPLookupException If the search operation fails.
	 */
	public NamingEnumeration<SearchResult> lookup(final String baseDN, final String filter, final List<String> attributes) throws LDAPLookupException {
		SearchControls controls = new SearchControls();
		controls.setDerefLinkFlag(true);
		String[] attrs = new String[attributes.size()];
		for (int i = 0; i < attributes.size(); i++) {
			attrs[i] = attributes.get(i);
		}
		controls.setReturningAttributes(attrs);
		controls.setReturningObjFlag(true);
		controls.setSearchScope(this.searchScope);
		controls.setTimeLimit(this.timeout);

		NamingEnumeration<SearchResult> results = null;
		try {
			results = this.context.search(baseDN, filter, controls);
		} catch (NamingException namingExc) {
			StringBuffer message = new StringBuffer();
			message.append("Error during lookup.");
			throw new LDAPLookupException(message.toString(), namingExc);
		}
		return results;
	}
	
	/**
	 * Returns the server attributes.
	 * @param server String containing the address of the server.
	 * @param attributes List of attributes to return in response.
	 * @return Hash map containing the values of the attributes required.
	 * @throws NamingException If an operation error occurs.
	 */
	public HashMap<String, List<String>> getServerAttributes(String server, List<String> attributes) throws NamingException {
		HashMap<String, List<String>> elements = new HashMap<>();
		
		String[] requiredAttributes = new String[attributes.size()];
		int index = 0;
		for(String item : attributes) {
			requiredAttributes[index++] = item;
		}
		
		Attributes attrs = this.context.getAttributes(server, requiredAttributes);
		NamingEnumeration<String> ids = attrs.getIDs();
		while(ids.hasMoreElements()) {
			String id = ids.nextElement();
			
			List<String> values = null;			
			if(elements.containsKey(id)) {
				values = elements.get(id);
			} else {
				values = new ArrayList<String>();
			}
			elements.put(id,  values);
				
			Attribute value = attrs.get(id);
			if(value.size() > 1) {
				NamingEnumeration<String> allValues = (NamingEnumeration<String>) value.getAll();
				while(allValues.hasMoreElements()) {
					String singleValue = allValues.nextElement();
					values.add(singleValue);
				}
			} else {
				String singleResult = (String) value.get(0);
				values.add(singleResult);
			}
		}
		return elements;
	}
	
	/**
	 * Modifies the value of a given attribute.
	 * @param items List of attributes to modify
	 * @param dn DN of the object to have its attributes changed.
	 * @throws NamingException If an error occurs.
	 */
	public void modifyAttribute(ModificationItem[] items, String dn) throws NamingException {
		this.context.modifyAttributes(dn, items);
	}
	
	/**
	 * Adds an object into the server.
	 * @param dn DN of the new object.
	 * @param attrs Attributes for the new object to be created.
	 * @throws NamingException If an error occurs.
	 */
	public void add(String dn, Attributes attrs) throws NamingException {
		this.context.createSubcontext(dn, attrs);
	}
	
	/**
	 * Removes an object from the LDAP server.
	 * @param dn DN of the element to be removed.
	 * @throws NamingException If an error occurs.
	 */
	public void remove(String dn) throws NamingException {
		this.context.destroySubcontext(dn);
	}
}
