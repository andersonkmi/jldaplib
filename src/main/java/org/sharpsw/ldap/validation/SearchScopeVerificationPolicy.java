package org.sharpsw.ldap.validation;

import org.sharpsw.ldap.exception.InvalidSearchScopeException;
import org.sharpsw.ldap.exception.LDAPException;
import org.sharpsw.ldap.services.LDAPResource;
import org.sharpsw.ldap.services.SearchScope;

import javax.annotation.Nonnull;

public class SearchScopeVerificationPolicy implements LDAPVerificationPolicy {
    @Override
    public void verify(@Nonnull LDAPResource resource) throws InvalidSearchScopeException {
        String scope = resource.getSearchScope();
        if (scope == null) {
            throw new InvalidSearchScopeException("Null search scope");
        }

        if (scope.isEmpty()) {
            throw new InvalidSearchScopeException("Empty scope.");
        }

        // Verifies the value
        SearchScope searchScope = SearchScope.findByCode(scope);
        if (searchScope == null) {
            throw new InvalidSearchScopeException("Invalid search scope value '" + scope + "'");
        }
    }
}
