package org.sharpsw.ldap.services;

import java.util.HashMap;

import org.sharpsw.ldap.exception.LDAPServiceCreationException;
import org.sharpsw.ldap.exception.MissingLDAPResourceException;
import org.sharpsw.ldap.exception.ServiceFactoryInitException;

/**
 * Esta classe serve como base para outras fábricas de serviços
 * LDAP desenvolvidas na biblioteca.
 * @author andersonkmi
 *
 */
public abstract class LDAPBaseServiceProviderFactory {
	private LDAPConnectionFactory factory;
	private LDAPResource resource;
	
	/**
	 * Construtor que recebe uma cadeia de caracteres contendo o caminho
	 * para o arquivo de recursos.
	 * @param resource String contendo o nome do arquivo de recursos.
	 * @throws ServiceFactoryInitException Caso ocorra um erro na inicialização da fábrica.
	 */
	public LDAPBaseServiceProviderFactory(String resource) throws ServiceFactoryInitException {
		try {
			this.resource = new LDAPResource();
			this.resource.setProperties(resource);
			this.factory = new LDAPConnectionFactory();
		} catch (MissingLDAPResourceException exception) {
			throw new ServiceFactoryInitException("Resource failure during service factory creation", exception);
		}
	}
	
	/**
	 * Construtor da classe que recebe o objeto contendo os parâmetros de conexão com o servidor LDAP.
	 * @param resource Objeto contendo os parâmetros de conexão com o servidor LDAP.
	 * @see org.sharpsw.ldap.services.LDAPResource
	 */
	public LDAPBaseServiceProviderFactory(LDAPResource resource) {
		this.resource = resource;
		this.factory = new LDAPConnectionFactory();
	}
	
	/**
	 * Construtor da classe que recebe um hashmap contendo os valores de parâmetros de conexão com o servidor LDAP.
	 * @param properties Hash map contendos os argumentos de conexão com o servidor
	 * @throws ServiceFactoryInitException Caso algum erro ocorra
	 */
	public LDAPBaseServiceProviderFactory(final HashMap<String, String> properties)	throws ServiceFactoryInitException {
		this.resource = new LDAPResource();
		this.resource.setProperties(properties);
		this.factory = new LDAPConnectionFactory();
	}
	
	public abstract ILDAPBaseService buildService() throws LDAPServiceCreationException;
	
	/**
	 * Returns the connection factory object to underlying services.
	 * @return Connection factory instance.
	 * @see org.sharpsw.ldap.services.LDAPConnectionFactory
	 */
	protected LDAPConnectionFactory getConnectionFactory() {
		return this.factory;
	}
	
	/**
	 * Returns the current LDAP resource object.
	 * @return Instance of the class <code>LDAPResource</code>.
	 * @see org.sharpsw.ldap.services.LDAPResource
	 */
	protected LDAPResource getLDAPResource() {
		return this.resource;
	}
}
