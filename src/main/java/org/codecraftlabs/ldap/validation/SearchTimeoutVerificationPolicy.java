package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidSearchTimeoutException;
import org.codecraftlabs.ldap.services.LDAPResource;

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
