package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidServerException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class ServerVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws LDAPException {
        String server = resource.getServer();
        if(server == null) {
            throw new InvalidServerException("Null server name supplied");
        }

        if(server.isEmpty()) {
            throw new InvalidServerException("Server name empty");
        }
    }
}
