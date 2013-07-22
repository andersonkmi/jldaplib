package org.sharpsw.ldap.services;

import java.util.HashMap;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPServiceCreationException;
import org.sharpsw.ldap.exception.ServiceFactoryInitException;
import org.sharpsw.ldap.util.DistinguishedNameBuilder;

/**
 * This class is a factory of the implementors of the <code>ILDAPRetrievalService</code> 
 * for the supported servers of this library.
 * @author Anderson Ito
 *
 */
public class LDAPRetrievalServiceFactory extends LDAPBaseServiceProviderFactory {

	/**
	 * Class constructor that accepts a string with the resources file name.
	 * @param resource String containing the name of the resource file to be loaded.
	 * @throws ServiceFactoryInitException If an initialization error occurs.
	 */
	public LDAPRetrievalServiceFactory(String resource) throws ServiceFactoryInitException {
		super(resource);
	}
	
	/**
	 * Constructor that accepts an instance of the class <code>LDAPResource</code> containing the
	 * configuration of the service.
	 * @param resource Instance of the class LDAPResource containing the configuration of the service.
	 */
	public LDAPRetrievalServiceFactory(LDAPResource resource) {
		super(resource);
	}

	/**
	 * Constructor that accepts a hash map containing the configuration required for the service.
	 * @param properties Hash map containing the service configuration.
	 * @throws ServiceFactoryInitException If an error occurs.
	 */
	public LDAPRetrievalServiceFactory(final HashMap<String, String> properties) throws ServiceFactoryInitException {
		super(properties);
	}
	
	/**
	 * Creates the service based upon the configuration information.
	 * @return Instance of a class that implements the <code>ILDAPRetrievalService</code> interface.
	 */
	@Override
	public ILDAPRetrievalService buildService() throws LDAPServiceCreationException {
		ILDAPRetrievalService service = null;

		if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.name())) {
			try {
				service = new org.sharpsw.ldap.services.apacheds.RetrievalServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource());
			} catch (LDAPException exception) {
				throw new LDAPServiceCreationException("Error when creating the admin service", exception);
			}
		} else if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.MS_ACTIVE_DIRECTORY_2003_SERVICE_PROVIDER.name())) {
			try {
				service = new org.sharpsw.ldap.services.msad.RetrievalServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource());
			} catch (LDAPException exception) {
				throw new LDAPServiceCreationException("Error when creating the service", exception);
			}
		} else if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.OPEN_LDAP_SERVICE_PROVIDER.name())) {
			try {
				service = new org.sharpsw.ldap.services.openldap.RetrievalServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource(), new DistinguishedNameBuilder());
			} catch (LDAPException exception) {
				throw new LDAPServiceCreationException("Error when creating the service", exception);
			}
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Invalid service provider supplied: ").append(this.getLDAPResource().getServerVendor());
			throw new LDAPServiceCreationException(buffer.toString());
		}
		return service;
	}

}
