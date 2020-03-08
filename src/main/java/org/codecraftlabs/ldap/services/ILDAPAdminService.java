package org.codecraftlabs.ldap.services;

import org.codecraftlabs.ldap.exception.LDAPUserPasswordChangeException;
import org.codecraftlabs.ldap.User;
import org.codecraftlabs.ldap.exception.LDAPUserAddException;
import org.codecraftlabs.ldap.exception.LDAPUserRemovalException;

/**
 * Esta interface apresenta os métodos para administração
 * de um servidor LDAP.
 * @author andersonkmi
 *
 */
public interface ILDAPAdminService extends ILDAPBaseService {
	/**
	 * Este método permite a adição de um novo usuário ao LDAP.
	 * @param user Instância da classe <code>User</code> a ser adicionada ao servidor.
	 * @throws LDAPUserAddException Caso ocorra um erro ao adicionar o usuário ao servidor.
	 * @see User
	 */
	void add(User user) throws LDAPUserAddException;
	
	/**
	 * Este método remove um usuário do servidor LDAP.
	 * @param user Instância da classe <code>User</code> a ser removida do servidor.
	 * @throws LDAPUserRemovalException Caso ocorra um erro ao remover o usuário.
	 */
	void remove(User user) throws LDAPUserRemovalException;
	
	/**
	 * Altera a senha de um usuário existente no servidor LDAP.
	 * @param user
	 * @param password
	 * @throws LDAPUserPasswordChangeException
	 */
	void changePassword(User user, String password) throws LDAPUserPasswordChangeException;

}
