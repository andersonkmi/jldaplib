package org.codecraftlabs.ldap.services.msad;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.exception.LDAPLookupException;
import org.codecraftlabs.ldap.services.ILDAPRetrievalService;
import org.codecraftlabs.ldap.services.LDAPResource;
import org.codecraftlabs.ldap.services.exception.LDAPFindException;
import org.codecraftlabs.ldap.Group;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.services.LDAPConnection;

public class RetrievalServiceImpl extends BaseService implements ILDAPRetrievalService {

	public RetrievalServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
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
	public User getUser(String uid, String baseDN) throws LDAPFindException {
		User user = null;
		String userName = null;
		String displayName = null;
		String dn = null;
		String cn = null;
		List<String> objectClassAttrs = new ArrayList<String>();

		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(UID);
			attributes.add(DISPLAY_NAME);
			attributes.add(DN);
			attributes.add(CN);
			attributes.add(OBJECT_CLASS);
			StringBuffer filter = new StringBuffer();
			filter.append("(&(").append(this.getUserIdAttribute()).append("=").append(uid).append(")(").append(USER_SEARCH_FILTER).append("))");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(),	attributes);

			if (!results.hasMore()) {
				StringBuffer message = new StringBuffer();
				message.append("No entry found identified by uid = '").append(uid).append("'");
				throw new LDAPFindException(message.toString());
			}

			SearchResult result = results.next();
			dn = result.getNameInNamespace();
			Attributes attrs = result.getAttributes();
			NamingEnumeration<? extends Attribute> attr = attrs.getAll();

			while (attr.hasMore()) {
				Attribute item = attr.next();
				String attrId = item.getID();

				if (attrId.equals(UID)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						userName = values.next().toString();
					}
				} else if (attrId.equals(DISPLAY_NAME)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						displayName = values.next().toString();
					}
				} else if (attrId.equals(DN)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						dn = values.next().toString();
					}
				} else if(attrId.equals(OBJECT_CLASS)) {
					NamingEnumeration<?> values = item.getAll();
					while(values.hasMore()) {
						String objectClass = values.next().toString();
						objectClassAttrs.add(objectClass);
					}
				} else if (attrId.equals(CN)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						cn = values.next().toString();
					}
				}
			}

			if (results.hasMore()) {
				StringBuffer message = new StringBuffer();
				message.append("Multiple entries found for uid = '").append(uid).append("'");
				throw new LDAPFindException(message.toString());
			}
			user = new User(userName, displayName, dn, cn);
			for(String item : objectClassAttrs) {
				user.addObjectClass(item);
			}
		} catch (LDAPLookupException exception) {
			throw new LDAPFindException("Lookup failed", exception);
		} catch (NamingException exception) {
			throw new LDAPFindException("Naming exception raised", exception);
		}
		return user;
	}

	@Override
	public User getUser(String uid) throws LDAPFindException {
		return getUser(uid, this.getLDAPResource().getUserBaseDN());	
	}

	@Override
	public List<User> getAllUsers(String baseDN) throws LDAPFindException {
		String userName = null;
		String displayName = null;
		String dn = null;
		String cn = null;
		List<String> objectClassAttrs = new ArrayList<String>();

		List<User> users = new ArrayList<User>();
		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(UID);
			attributes.add(DISPLAY_NAME);
			attributes.add(DN);
			attributes.add(CN);
			attributes.add(OBJECT_CLASS);
			StringBuffer filter = new StringBuffer();
			filter.append("(").append(USER_SEARCH_FILTER).append(")");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(), attributes);

			while (results.hasMore()) {
				SearchResult result = results.next();
				dn = result.getNameInNamespace();
				Attributes attrs = result.getAttributes();
				NamingEnumeration<? extends Attribute> attr = attrs.getAll();

				while (attr.hasMore()) {
					Attribute item = attr.next();
					String attrId = item.getID();

					if (attrId.equals(UID)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							userName = values.next().toString();
						}
					} else if (attrId.equals(DISPLAY_NAME)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							displayName = values.next().toString();
						}
					} else if (attrId.equals(DN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							dn = values.next().toString();
						}
					} else if(attrId.equals(OBJECT_CLASS)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							String objectClass = values.next().toString();
							objectClassAttrs.add(objectClass);
						}
					} else if (attrId.equals(CN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							cn = values.next().toString();
						}
					}
				}

				User user = new User(userName, displayName, dn, cn);
				users.add(user);
				for(String item : objectClassAttrs) {
					user.addObjectClass(item);
				}
			}
		} catch (LDAPLookupException exception) {
			throw new LDAPFindException("Lookup failed", exception);
		} catch (NamingException exception) {
			throw new LDAPFindException("Naming exception raised", exception);
		}
		
		Collections.sort(users);
		return users;
	}

	@Override
	public List<User> getAllUsers() throws LDAPFindException {
		return this.getAllUsers(this.getLDAPResource().getUserBaseDN());
	}

	@Override
	public List<Group> getGroups(String baseDN) throws LDAPFindException {
		return null;
	}

	@Override
	public List<Group> getGroups() throws LDAPFindException {
		return null;
	}

	@Override
	public Group getGroup(String groupName, String baseDN) throws LDAPFindException {
		return null;
	}

	@Override
	public Group getGroup(String groupName) throws LDAPFindException {
		return null;
	}

}
