package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidBindPasswordException;
import org.codecraftlabs.ldap.exception.InvalidBindUserException;
import org.codecraftlabs.ldap.exception.InvalidEncryptionMethodException;
import org.codecraftlabs.ldap.exception.InvalidGroupBaseDNException;
import org.codecraftlabs.ldap.exception.InvalidInitialContextFactoryException;
import org.codecraftlabs.ldap.exception.InvalidPortException;
import org.codecraftlabs.ldap.exception.InvalidResourceException;
import org.codecraftlabs.ldap.exception.InvalidSearchScopeException;
import org.codecraftlabs.ldap.exception.InvalidSearchTimeoutException;
import org.codecraftlabs.ldap.exception.InvalidSecurityAuthenticationException;
import org.codecraftlabs.ldap.exception.InvalidServerException;
import org.codecraftlabs.ldap.exception.InvalidServerVendorException;
import org.codecraftlabs.ldap.exception.InvalidUserBaseDNException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;
import java.util.Set;

public class LDAPPolicyValidator implements LDAPPolicyVerifier {
    private Set<LDAPVerificationPolicy> policies;

    public LDAPPolicyValidator(Set<LDAPVerificationPolicy> policies) {
        this.policies = policies;
    }

    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidBindPasswordException,
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
            InvalidUserBaseDNException {
        for (LDAPVerificationPolicy policy : policies) {
            policy.verify(resource);
        }
    }
}
