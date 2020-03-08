package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidBindUserException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

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
