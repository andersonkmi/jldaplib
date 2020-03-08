package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidServerException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class ServerVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidServerException {
        String server = resource.getServer();
        if(server == null) {
            throw new InvalidServerException("Null server name supplied");
        }

        if(server.isEmpty()) {
            throw new InvalidServerException("Server name empty");
        }
    }
}
