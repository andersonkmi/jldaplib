package org.sharpsw.ldap.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.sharpsw.ldap.exception.MissingLDAPResourceException;

/**
 * This class wraps the configuration information for the LDAP service.
 * @author Anderson Ito
 *
 */
public class LDAPResource implements Cloneable {
	private Map<String, String> properties;

	public static final String CONTEXT_FACTORY = "sharpsw.context.factory";
	public static final String SERVER = "sharpsw.ldap.server";
	public static final String PORT = "sharpsw.ldap.port";
	public static final String ENCRYPTION = "sharpsw.ldap.encryption";
	public static final String BIND_USER = "sharpsw.ldap.binduser";
	public static final String PASSWORD = "sharpsw.ldap.password";
	public static final String SEARCH_SCOPE = "sharpsw.ldap.search_scope";
	public static final String SEARCH_TIMEOUT = "sharpsw.ldap.search_timeout";
	public static final String SECURITY_AUTH = "sharpsw.ldap.security_auth";
	public static final String SERVER_VENDOR = "sharpsw.ldap.server_vendor";
	public static final String USER_BASE_DN = "sharpsw.ldap.user_base_dn";
	public static final String GROUP_BASE_DN = "sharpsw.ldap.group_base_dn";

	/**
	 * Class constructor.
	 */
	public LDAPResource() {
		this.properties = new HashMap<>();
	}

	/**
	 * Creates a clone of the object.
	 * @return New instance of this class with the same attribute values of the original instance.
	 */
	public final LDAPResource clone() throws CloneNotSupportedException {
		super.clone();
		HashMap<String, String> settings = new HashMap<>();
		settings.put(CONTEXT_FACTORY, this.properties.get(CONTEXT_FACTORY));
		settings.put(SERVER, this.properties.get(SERVER));
		settings.put(BIND_USER, this.properties.get(BIND_USER));
		settings.put(PASSWORD, this.properties.get(PASSWORD));
		settings.put(SEARCH_SCOPE, this.properties.get(SEARCH_SCOPE));
		settings.put(SEARCH_TIMEOUT, this.properties.get(SEARCH_TIMEOUT));
		settings.put(SECURITY_AUTH, this.properties.get(SECURITY_AUTH));
		settings.put(PORT, this.properties.get(PORT));
		settings.put(ENCRYPTION, this.properties.get(ENCRYPTION));
		settings.put(SERVER_VENDOR, this.properties.get(SERVER_VENDOR));
		settings.put(USER_BASE_DN, this.properties.get(USER_BASE_DN));
		settings.put(GROUP_BASE_DN, this.properties.get(GROUP_BASE_DN));
		LDAPResource localResource = new LDAPResource();
		localResource.setProperties(settings);
		return localResource;
	}

	/**
	 * Sets the LDAP server connection properties given a HashMap with the
	 * configuration values.
	 * @param externalProperties
	 *            HashMap containing the connection properties.
	 */
	public final void setProperties(final HashMap<String, String> externalProperties) {
		if (this.properties != null) {
			this.properties.clear();
		} else {
			this.properties = new HashMap<>();
		}

		Set<Entry<String, String>> entries = externalProperties.entrySet();
		for (Entry<String, String> item : entries) {
			this.properties.put(item.getKey(), item.getValue());
		}
	}

	/**
	 * Sets the LDAP server connection properties using an external file.
	 * @param propertiesFile
	 *            String containing the file path to the properties file.
	 * @throws MissingLDAPResourceException
	 *             If the file is not accessible.
	 */
	public final void setProperties(final String propertiesFile) throws MissingLDAPResourceException {
		FileReader file = null;
		BufferedReader reader = null;
		try {
			// Removes all previous properties information
			if (this.properties != null) {
				this.properties.clear();
			} else {
				this.properties = new HashMap<>();
			}

			Properties props = new Properties();
			file = new FileReader(propertiesFile);
			reader = new BufferedReader(file);
			props.load(reader);

			this.properties.put(LDAPResource.SERVER_VENDOR,	props.getProperty(LDAPResource.SERVER_VENDOR));
			this.properties.put(LDAPResource.CONTEXT_FACTORY, props.getProperty(LDAPResource.CONTEXT_FACTORY));
			this.properties.put(LDAPResource.SERVER, props.getProperty(LDAPResource.SERVER));
			this.properties.put(LDAPResource.BIND_USER, props.getProperty(LDAPResource.BIND_USER));
			this.properties.put(LDAPResource.PASSWORD, props.getProperty(LDAPResource.PASSWORD));
			this.properties.put(LDAPResource.SEARCH_SCOPE, props.getProperty(LDAPResource.SEARCH_SCOPE));
			this.properties.put(LDAPResource.SEARCH_TIMEOUT, props.getProperty(LDAPResource.SEARCH_TIMEOUT));
			this.properties.put(LDAPResource.SECURITY_AUTH, props.getProperty(LDAPResource.SECURITY_AUTH));
			this.properties.put(LDAPResource.PORT, props.getProperty(LDAPResource.PORT));
			this.properties.put(LDAPResource.ENCRYPTION, props.getProperty(LDAPResource.ENCRYPTION));
			this.properties.put(LDAPResource.USER_BASE_DN, props.getProperty(LDAPResource.USER_BASE_DN));
			this.properties.put(LDAPResource.GROUP_BASE_DN,	props.getProperty(LDAPResource.GROUP_BASE_DN));

		} catch (IOException fileNotFoundExc) {
			StringBuffer log = new StringBuffer("LDAP resource file ");
			log.append(propertiesFile).append(" could not be found or it is not accessible");
			throw new MissingLDAPResourceException(log.toString(), fileNotFoundExc);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioExc) {
					//System.err.println(ioExc.getMessage());
				}
			}
			if (file != null) {
				try {
					file.close();
				} catch (IOException ioExc) {
					//System.err.println(ioExc.getMessage());
				}
			}
		}
	}

	/**
	 * Sets a single LDAP server connection property.
	 * @param key
	 *            LDAP connection configuration property name.
	 * @param value
	 *            LDAP connection configuration property value.
	 */
	public final void setProperty(final String key, final String value) {
		this.properties.put(key, value);
	}

	/**
	 * Returns the server information.
	 * @return LDAP server name
	 */
	public final String getServer() {
		String server = "";
		server = this.properties.get(LDAPResource.SERVER);
		return server;
	}

	/**
	 * Returns the server vendor type information.
	 * @return LDAP server vendor. The currently supported vendors are: Apache
	 *         Directory Server, Active Directory 2003 and Open LDAP.
	 */
	public final String getServerVendor() {
		return this.properties.get(LDAPResource.SERVER_VENDOR);
	}

	/**
	 * Returns the LDAP bind user that must be used when connecting to the LDAP
	 * server.
	 * @return LDAP server bind user
	 */
	public final String getBindUser() {
		return this.properties.get(LDAPResource.BIND_USER);
	}

	/**
	 * Returns the bind user password information.
	 * @return LDAP bind user password
	 */
	public final String getPassword() {
		return this.properties.get(LDAPResource.PASSWORD);
	}

	/**
	 * Returns the initial context factory information.
	 * @return String containing the context factory information.
	 */
	public final String getInitialContextFactory() {
		return this.properties.get(LDAPResource.CONTEXT_FACTORY);
	}

	/**
	 * Return the search scope configured for the service.
	 * @return String containing the search scope
	 */
	public final String getSearchScope() {
		return this.properties.get(LDAPResource.SEARCH_SCOPE);
	}

	/**
	 * Returns the search timeout information.
	 * @return LDAP search timeout configuration
	 */
	public final String getSearchTimeout() {
		return this.properties.get(LDAPResource.SEARCH_TIMEOUT);
	}

	/**
	 * Returns the authentication type.
	 * @return Authentication type information
	 */
	public final String getSecurityAuthentication() {
		return this.properties.get(LDAPResource.SECURITY_AUTH);
	}

	/**
	 * Returns the port number information.
	 * @return Port number information
	 */
	public final String getPort() {
		return this.properties.get(LDAPResource.PORT);
	}
	
	/**
	 * Returns the encryption method used in the connection
	 * @return Encryption method. The possible values are 'none', 'ssl' or 'tls'
	 */
	public final String getEncryption() {
		return this.properties.get(LDAPResource.ENCRYPTION);
	}

	/**
	 * Returns the base dn information.
	 * @return Base DN configuration value
	 */
	public final String getUserBaseDN() {
		return this.properties.get(USER_BASE_DN);
	}

	/**
	 * Returns the group base dn information.
	 * @return Group base DN configuration value
	 */
	public final String getGroupBaseDN() {
		return this.properties.get(GROUP_BASE_DN);
	}

	/**
	 * Returns a string representation of the object.
	 * @return String containing some of the information of the instance.
	 */
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Server: ").append(this.properties.get(LDAPResource.SERVER));
		buffer.append("; Bind: ").append(this.properties.get(LDAPResource.BIND_USER));
		buffer.append("; User: ").append(this.properties.get(LDAPResource.BIND_USER));
		return buffer.toString();
	}

	/**
	 * Returns the has code associated with this instance.
	 * @return Integer number representing the hash code.
	 */
	public final int hashCode() {
		return this.properties.hashCode();
	}
	
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		
		if(!this.getClass().equals(other.getClass())) {
			return false;
		}
		
		LDAPResource instance = (LDAPResource) other;
		
		return this.properties.equals(instance.properties);
	}
}
