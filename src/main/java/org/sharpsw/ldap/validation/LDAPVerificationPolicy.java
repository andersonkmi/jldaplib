package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidBindPasswordException;
import org.sharpsw.ldap.exception.InvalidBindUserException;
import org.sharpsw.ldap.exception.InvalidEncryptionMethodException;
import org.sharpsw.ldap.exception.InvalidGroupBaseDNException;
import org.sharpsw.ldap.exception.InvalidInitialContextFactoryException;
import org.sharpsw.ldap.exception.InvalidPortException;
import org.sharpsw.ldap.exception.InvalidResourceException;
import org.sharpsw.ldap.exception.InvalidSearchScopeException;
import org.sharpsw.ldap.exception.InvalidSearchTimeoutException;
import org.sharpsw.ldap.exception.InvalidSecurityAuthenticationException;
import org.sharpsw.ldap.exception.InvalidServerException;
import org.sharpsw.ldap.exception.InvalidServerVendorException;
import org.sharpsw.ldap.exception.InvalidUserBaseDNException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public interface LDAPVerificationPolicy {
    void verify(@Nonnull final LDAPResource resource) throws InvalidBindPasswordException,
            InvalidBindUserException,
            InvalidGroupBaseDNException,
            InvalidInitialContextFactoryException,
            InvalidPortException,
            InvalidResourceException,
            InvalidSearchScopeException,
            InvalidSearchTimeoutException,
            InvalidSecurityAuthenticationException,
            InvalidServerException,
            InvalidServerVendorException,
            InvalidEncryptionMethodException,
            InvalidUserBaseDNException;
}
