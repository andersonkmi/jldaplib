package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidSearchTimeoutException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class SearchTimeoutVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidSearchTimeoutException {
        String timeout = resource.getSearchTimeout();
        if (timeout == null) {
            throw new InvalidSearchTimeoutException("Null timeout");
        }

        if (timeout.isEmpty()) {
            throw new InvalidSearchTimeoutException("Empty timeout information");
        }
    }
}
