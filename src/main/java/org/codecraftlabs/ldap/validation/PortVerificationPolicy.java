package org.codecraftlabs.ldap.validation;

import org.codecraftlabs.ldap.exception.InvalidPortException;
import org.codecraftlabs.ldap.services.LDAPResource;

import javax.annotation.Nonnull;

public class PortVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidPortException {
        String port = resource.getPort();

        if(port == null) {
            throw new InvalidPortException("Null port supplied.");
        } else if(port.isEmpty()) {
            throw new InvalidPortException("Empty port number supplied.");
        } else {
            try {
                int portNumber = Integer.parseInt(port);
                if(portNumber < 1) {
                    throw new InvalidPortException("Port number is invalid: " + portNumber);
                }
            } catch (NumberFormatException exception) {
                throw new InvalidPortException("Invalid number supplied", exception);
            }
        }

    }
}
