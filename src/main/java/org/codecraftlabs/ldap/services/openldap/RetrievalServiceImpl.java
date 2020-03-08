package org.codecraftlabs.ldap.services.openldap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import org.codecraftlabs.ldap.util.DistinguishedName;
import org.codecraftlabs.ldap.util.DistinguishedNameBuilder;
import org.codecraftlabs.ldap.util.DistinguishedNameNodeType;
import org.codecraftlabs.ldap.Group;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.services.LDAPConnection;

public class RetrievalServiceImpl extends BaseService implements ILDAPRetrievalService {
	public RetrievalServiceImpl(final LDAPConnection connection, final LDAPResource resource, DistinguishedNameBuilder builder) {
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
			filter.append("(&(").append(this.getUserIdAttribute()).append("=").append(uid).append(")(").append(this.getUserSearchFilter()).append("))");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(), attributes);

			if (!results.hasMore()) {
				StringBuffer message = new StringBuffer();
				message.append("No entry found identified by ").append(this.getUserIdAttribute()).append(" = '").append(uid).append("'");
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
			filter.append("(").append(this.getUserSearchFilter()).append(")");
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
					} else if (attrId.equals(OBJECT_CLASS)) {
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
		return getAllUsers(this.getLDAPResource().getUserBaseDN());
	}

	@Override
	public List<Group> getGroups(String baseDN) throws LDAPFindException {
		List<Group> groups = new ArrayList<Group>();
		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(CN);
			attributes.add(GID);
			attributes.add(UNIQUE_MEMBER_UID);
			attributes.add(UNIQUE_MEMBER_CN);
			attributes.add(DN);

			StringBuffer filter = new StringBuffer();
			filter.append("(").append(this.getGroupSearchFilter()).append(")");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(), attributes);

			String dn = "";
			String name = "";
			Group group = null;

			while (results.hasMore()) {
				SearchResult result = results.next();
				dn = result.getNameInNamespace();
				Attributes attrs = result.getAttributes();
				NamingEnumeration<? extends Attribute> attr = attrs.getAll();

				List<User> userMembers = new ArrayList<User>();				
				List<String> members = new ArrayList<String>();
				while (attr.hasMore()) {
					Attribute item = attr.next();
					String attrId = item.getID();

					if (attrId.equals(CN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							name = values.next().toString();
						}
					} else if (attrId.equals(UNIQUE_MEMBER_UID)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							String uid = values.next().toString();
							members.add(uid);
							RetrievalServiceImpl service = new RetrievalServiceImpl(getLDAPConnection(), getLDAPResource(), this.builder);
							try {
								service.setUserIdAttribute("uid");
								User user = service.getUser(uid);
								userMembers.add(user);								
							} catch (LDAPFindException exception) {
								// No action performed
							}
						}
					} else if (attrId.equals(UNIQUE_MEMBER_CN)) {
						NamingEnumeration<?> values = item.getAll();
						while (values.hasMore()) {
							String commonName = values.next().toString();
							DistinguishedName memberDN = this.builder.build(commonName);
							RetrievalServiceImpl service = new RetrievalServiceImpl(this.getLDAPConnection(), this.getLDAPResource(), this.builder);
							try {
								service.setUserIdAttribute(DistinguishedNameNodeType.CN.getCode());
								User user = service.getUser(memberDN.peek().getValue());
								userMembers.add(user);
								members.add(commonName);								
							} catch (LDAPFindException exception) {
								// No action performed
							}
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
				
				for(User userMember : userMembers) {
					group.add(userMember);
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
		List<User> userMembers = new ArrayList<User>();
		try {
			NamingEnumeration<SearchResult> results = null;
			List<String> attributes = new ArrayList<String>();
			attributes.add(UNIQUE_MEMBER_UID);
			attributes.add(CN);
			attributes.add(UNIQUE_MEMBER_CN);
			attributes.add(DN);
			StringBuffer filter = new StringBuffer();
			filter.append("(&(").append(this.getGroupIdAttribute()).append("=").append(groupName).append(")(").append(this.getGroupSearchFilter()).append("))");
			results = this.getLDAPConnection().lookup(baseDN, filter.toString(), attributes);

			if (!results.hasMore()) {
				StringBuffer message = new StringBuffer();
				message.append("No entry found identified by cn = '").append(groupName).append("'");
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
				} else if (attrId.equals(UNIQUE_MEMBER_UID)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						String uid = values.next().toString(); 
						members.add(uid);
						RetrievalServiceImpl service = new RetrievalServiceImpl(this.getLDAPConnection(), this.getLDAPResource(), this.builder);
						try {
							User user = service.getUser(uid);
							userMembers.add(user);							
						} catch (LDAPFindException exception) {
							// No action performed
						}
					}
				} else if (attrId.equals(UNIQUE_MEMBER_CN)) {
					NamingEnumeration<?> values = item.getAll();
					while (values.hasMore()) {
						String commonName = values.next().toString(); 
						members.add(commonName);
						DistinguishedName memberDN = this.builder.build(commonName);

						RetrievalServiceImpl service = new RetrievalServiceImpl(this.getLDAPConnection(), this.getLDAPResource(), this.builder);
						try {
							service.setUserIdAttribute(DistinguishedNameNodeType.CN.getCode());
							User user = service.getUser(memberDN.peek().getValue());
							userMembers.add(user);							
						} catch (LDAPFindException exception) {
							// No action performed
						}
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
			
			for(User user : userMembers) {
				group.add(user);
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
