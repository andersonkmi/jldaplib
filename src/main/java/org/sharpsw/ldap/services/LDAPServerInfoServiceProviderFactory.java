package org.sharpsw.ldap.services;

import java.util.HashMap;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPServiceCreationException;
import org.sharpsw.ldap.exception.ServiceFactoryInitException;
import org.sharpsw.ldap.util.DistinguishedNameBuilder;

/**
 * This class is a factory of objects that implement the <code>ILDAPServerInfoService</code> interface.
 * @author Anderson Ito
 *
 */
public class LDAPServerInfoServiceProviderFactory extends LDAPBaseServiceProviderFactory {
	
	/**
	 * Constructor that accepts a string containing the file name of the resource file.
	 * @param resource String containing the file name of the resource file.
	 * @throws ServiceFactoryInitException If an error occurs.
	 */
	public LDAPServerInfoServiceProviderFactory(String resource) throws ServiceFactoryInitException {
		super(resource);
	}
	
	/**
	 * Constructor that accepts an instance of the class <code>LDAPResource</code>.
	 * @param resource Instance of object containing configuration information.
	 */
	public LDAPServerInfoServiceProviderFactory(LDAPResource resource) {
		super(resource);
	}
	
	/**
	 * Constructor that accepts a hash map containing the service configuration.
	 * @param properties Hash map with the service configuration.
	 * @throws ServiceFactoryInitException If an error occurs.
	 */
	public LDAPServerInfoServiceProviderFactory(final HashMap<String, String> properties) throws ServiceFactoryInitException {
		super(properties);
	}

	/**
	 * Builds the service based upon the configuration supplied.
	 * @return Instance of a class that implements the <code>ILDAPServerInfoService</code> interface.
	 */
	@Override
	public ILDAPServerInfoService buildService() throws LDAPServiceCreationException {
		ILDAPServerInfoService service = null;
		if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.name())) {
			try {
				service = new org.sharpsw.ldap.services.apacheds.ServerInfoServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource());
			} catch (LDAPException exception) {
				throw new LDAPServiceCreationException("Error when creating the admin service", exception);
			}
		} else if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.MS_ACTIVE_DIRECTORY_2003_SERVICE_PROVIDER.name())) {
			//try {
			//	service = new MSActiveDirectory2003ServiceProviderImpl(this.factory.getConnection(this.resource), this.resource);
			//} catch (LDAPException exception) {
			//	throw new LDAPServiceCreationException("Error when creating the service", exception);
			//}
		} else if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.OPEN_LDAP_SERVICE_PROVIDER.name())) {
			try {
				service = new org.sharpsw.ldap.services.openldap.ServerInfoServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource(), new DistinguishedNameBuilder());
			} catch (LDAPException exception) {
				throw new LDAPServiceCreationException("Error when creating the admin service", exception);
			}
		} 
		else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Invalid service provider supplied: ").append(this.getLDAPResource().getServerVendor());
			throw new LDAPServiceCreationException(buffer.toString());
		}
		return service;
	}

}
