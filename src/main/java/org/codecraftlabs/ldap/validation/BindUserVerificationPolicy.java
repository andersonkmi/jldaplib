package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidBindUserException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class BindUserVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidBindUserException {
        String bindUser = resource.getBindUser();
        if(bindUser == null) {
            throw new InvalidBindUserException("Null bind user supplied.");
        }

        if(bindUser.isEmpty()) {
            throw new InvalidBindUserException("Bind user name empty.");
        }
    }
}
