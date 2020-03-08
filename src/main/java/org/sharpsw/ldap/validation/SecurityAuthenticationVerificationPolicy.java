package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidSecurityAuthenticationException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

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
