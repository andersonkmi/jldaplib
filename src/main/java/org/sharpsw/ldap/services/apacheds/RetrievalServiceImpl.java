package org.sharpsw.ldap.services.apacheds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.sharpsw.ldap.Group;
import org.sharpsw.ldap.User;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPLookupException;
import org.sharpsw.ldap.services.ILDAPRetrievalService;
import org.sharpsw.ldap.services.LDAPConnection;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.exception.LDAPFindException;

/**
 * Implementation of the ILDAPRetrievalService interface for Apache DS.
 * @author Anderson Ito
 *
 */
public final class RetrievalServiceImpl extends BaseService implements ILDAPRetrievalService {
	
	/**
	 * Class constructor that receives the ldap connection object and the resource instance.
	 * @param connection Instance of the class <code>LDAPConnection</code>
	 * @param resource Instance of the class <code>LDAPResource</code>
	 */
	public RetrievalServiceImpl(final LDAPConnection connection, final LDAPResource resource) {
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
	 * Searches for an user in the LDAP server.
	 * @param uid User id information.
	 * @param baseDN Starting point of the search
	 * @return Instance of the class <code>User</code> holding the user's information in the LDAP server.
	 */
	@Override
	public User getUser(String uid, String baseDN) throws LDAPFindException {
		User user = null;
		String userName = null;
		String displayName = null;
		String dn = null;
		String cn = null;
		List<String> objectClassAttributes = new ArrayList<String>();

		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(UID);
			attributes.add(DISPLAY_NAME);
			attributes.add(DN);
			attributes.add(CN);
			attributes.add(OBJECT_CLASS);
			StringBuffer filter = new StringBuffer();
			filter.append("(&(").append(this.getUserIdentificationAttribute()).append("=").append(uid).append(")(").append(USER_SEARCH_FILTER).append("))");
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
						String attribute = values.next().toString();
						objectClassAttributes.add(attribute);
					}
				} else if(attrId.equals(CN)) {
					NamingEnumeration<?> values = item.getAll();
					while(values.hasMore()) {
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
			for(String value : objectClassAttributes) {
				user.addObjectClass(value);
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
						while(values.hasMore()) {
							String value = values.next().toString();
							objectClassAttrs.add(value);
						}
					} else if(attrId.equals(CN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							cn = values.next().toString();
						}
					}
				}

				User user = new User(userName, displayName, dn, cn);
				users.add(user);
				for(String value : objectClassAttrs) {
					user.addObjectClass(value);
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
		return getAllUsers(this.getLDAPResource().getUserBaseDN());
	}

	@Override
	public List<Group> getGroups(String baseDN) throws LDAPFindException {
		List<Group> groups = new ArrayList<Group>();
		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(CN);
			attributes.add(UNIQUE_MEMBER);
			attributes.add(DN);

			StringBuffer filter = new StringBuffer();
			filter.append("(").append(GROUP_SEARCH_FILTER).append(")");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(), attributes);

			String dn = "";
			String name = "";
			Group group = null;

			while (results.hasMore()) {
				SearchResult result = results.next();
				dn = result.getNameInNamespace();
				Attributes attrs = result.getAttributes();
				NamingEnumeration<? extends Attribute> attr = attrs.getAll();

				List<String> members = new ArrayList<String>();
				while (attr.hasMore()) {
					Attribute item = attr.next();
					String attrId = item.getID();

					if (attrId.equals(CN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							name = values.next().toString();
						}
					} else if (attrId.equals(UNIQUE_MEMBER)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							members.add(values.next().toString());
						}
					} else if (attrId.equals(DN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							dn = values.next().toString();
						}
					}
				}

				group = new Group(name, dn);
				for (String item : members) {
					group.add(item);
				}

				groups.add(group);
			}
		} catch (LDAPLookupException exception) {
			throw new LDAPFindException("Lookup failed", exception);
		} catch (NamingException exception) {
			throw new LDAPFindException("Naming exception raised", exception);
		}
		
		Collections.sort(groups);
		return groups;
	}

	@Override
	public List<Group> getGroups() throws LDAPFindException {
		return getGroups(this.getLDAPResource().getGroupBaseDN());
	}

	@Override
	public Group getGroup(String groupName, String baseDN) throws LDAPFindException {
		Group group = null;
		String name = null;
		String dn = null;
		List<String> members = new ArrayList<String>();

		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(UNIQUE_MEMBER);
			attributes.add(CN);
			attributes.add(DN);
			StringBuffer filter = new StringBuffer();
			filter.append("(&(").append(CN).append("=").append(groupName).append(")(").append(GROUP_SEARCH_FILTER).append("))");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(), attributes);

			if (!results.hasMore()) {
				StringBuffer message = new StringBuffer();
				message.append("No entry found identified by cn = '")
						.append(groupName).append("'");
				throw new LDAPFindException(message.toString());
			}

			SearchResult result = results.next();
			dn = result.getNameInNamespace();
			Attributes attrs = result.getAttributes();
			NamingEnumeration<? extends Attribute> attr = attrs.getAll();

			while (attr.hasMore()) {
				Attribute item = attr.next();
				String attrId = item.getID();

				if (attrId.equals(CN)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						name = values.next().toString();
					}
				} else if (attrId.equals(DN)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						dn = values.next().toString();
					}
				} else if (attrId.equals(UNIQUE_MEMBER)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						members.add(values.next().toString());
					}
				}
			}

			if (results.hasMore()) {
				StringBuffer message = new StringBuffer();
				message.append("Multiple entries found for cn = '").append(groupName).append("'");
				throw new LDAPFindException(message.toString());
			}
			group = new Group(name, dn);
			for (String item : members) {
				group.add(item);
			}
		} catch (LDAPLookupException exception) {
			throw new LDAPFindException("Lookup failed", exception);
		} catch (NamingException exception) {
			throw new LDAPFindException("Naming exception raised", exception);
		}
		return group;
	}

	@Override
	public Group getGroup(String groupName) throws LDAPFindException {
		return getGroup(groupName, this.getLDAPResource().getGroupBaseDN());
	}
}
