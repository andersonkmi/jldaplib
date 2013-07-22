package org.sharpsw.ldap.services;

import java.util.HashMap;

import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.exception.LDAPServiceCreationException;
import org.sharpsw.ldap.exception.ServiceFactoryInitException;
import org.sharpsw.ldap.util.DistinguishedNameBuilder;

/**
 * This class is a factory of objects that implement the ILDAPAdminService interface.
 * @author Anderson Ito
 * @see org.sharpsw.ldap.services.ILDAPAdminService
 *
 */
public class LDAPAdminServiceProviderFactory extends LDAPBaseServiceProviderFactory {

	/**
	 * Constructor that accepts a string containing the path to the resource file.
	 * @param resource String containing the name of the resource file to be loaded into the library.
	 * @throws ServiceFactoryInitException If an initialization error occurs.
	 */
	public LDAPAdminServiceProviderFactory(String resource) throws ServiceFactoryInitException {
		super(resource);
	}
	
	/**
	 * Constructor that accepts an instance of the class <code>LDAPResource</code> in order to initialize the service.
	 * @param resource Object of the class <code>LDAPResource</code> containing the service configurations.
	 * @see org.sharpsw.ldap.services.LDAPResource
	 */
	public LDAPAdminServiceProviderFactory(LDAPResource resource) {
		super(resource);
	}
	
	/**
	 * Constructor that accepts a hash map containing the LDAP service key configurations.
	 * @param properties Hash map containing the service configuration elements.
	 * @throws ServiceFactoryInitException If an initialization error occurs.
	 */
	public LDAPAdminServiceProviderFactory(final HashMap<String, String> properties) throws ServiceFactoryInitException {
		super(properties);
	}
	

	/**
	 * Creates the concrete service based upon the configurations informed.
	 * @return Respective implementation of the service.
	 * @see org.sharpsw.ldap.services.ILDAPAdminService
	 */
	@Override
	public ILDAPAdminService buildService() throws LDAPServiceCreationException {
		ILDAPAdminService service = null;
		if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.name())) {
			try {
				service = new org.sharpsw.ldap.services.apacheds.AdminServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource());
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
				service = new org.sharpsw.ldap.services.openldap.AdminServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource(), new DistinguishedNameBuilder());
			} catch (LDAPException exception) {
				throw new LDAPServiceCreationException("Error when creating the service", exception);
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
