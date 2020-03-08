package org.codecraftlabs.ldap.services;

import java.util.HashMap;

import org.codecraftlabs.ldap.exception.LDAPException;
import org.codecraftlabs.ldap.exception.LDAPServiceCreationException;
import org.codecraftlabs.ldap.exception.ServiceFactoryInitException;
import org.codecraftlabs.ldap.services.apacheds.AuthenticationServiceImpl;
import org.codecraftlabs.ldap.util.DistinguishedNameBuilder;

/**
 * Esta classe cria instâncias de serviços para autenticação em servidores LDAP.
 * @author andersonkmi
 *
 */
public class LDAPAuthServiceProviderFactory extends	LDAPBaseServiceProviderFactory {

	/**
	 * Construtor que recebe uma string contendo o nome do arquivo de recurso.
	 * @param resource
	 * @throws ServiceFactoryInitException
	 */
	public LDAPAuthServiceProviderFactory(String resource) throws ServiceFactoryInitException {
		super(resource);
	}

	/**
	 * Constructor that accepts an instance of the class <code>LDAPResource</code> to initialize the service.
	 * @param resource Instance of the class <code>LDAPResource</code>
	 * @see LDAPResource
	 */
	public LDAPAuthServiceProviderFactory(LDAPResource resource) { 
		super(resource);
	}
	
	/**
	 * Constructor that accepts a hash map containing the configurations for the service.
	 * @param properties Hash map containing the service configurations.
	 * @throws ServiceFactoryInitException
	 */
	public LDAPAuthServiceProviderFactory(final HashMap<String, String> properties)	throws ServiceFactoryInitException {
		super(properties);
	}
	
	/**
	 * Creates the concrete service.
	 * @return Object that implements the interface ILDAPAuthenticationService based on the selected server type.
	 * @see ILDAPAuthenticationService
	 */
	@Override
	public ILDAPAuthenticationService buildService() throws LDAPServiceCreationException {
		ILDAPAuthenticationService service = null;

		if (this.getLDAPResource().getServerVendor().equals(LDAPServiceProviderType.APACHEDS_SERVICE_PROVIDER.name())) {
			try {
				service = new AuthenticationServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource());
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
				service = new org.codecraftlabs.ldap.services.openldap.AuthenticationServiceImpl(this.getConnectionFactory().getConnection(this.getLDAPResource()), this.getLDAPResource(), new DistinguishedNameBuilder());
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
