package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidSecurityAuthenticationException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class SecurityAuthenticationVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidSecurityAuthenticationException {
        String auth = resource.getSecurityAuthentication();

        if (auth == null) {
            throw new InvalidSecurityAuthenticationException("Null security authentication");
        }

        if (auth.isEmpty()) {
            throw new InvalidSecurityAuthenticationException("Empty security authentication");
        }

        // Checks the supplied value
        if (!auth.equals("simple") && !auth.equals("none")) {
            throw new InvalidSecurityAuthenticationException("The valid values are: simple or none");
        }
    }
}
