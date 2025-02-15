package com.aprozeanu.smt.auth.configuration;

import com.aprozeanu.smt.auth.model.Role;
import com.aprozeanu.smt.auth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthenticationImpl implements Authentication {
    private final Principal principal;
    private final List<AuthRole> roles;

    public AuthenticationImpl(User user) {
        this.principal = new Principal(user.getUserId(), user.getUsername());
        this.roles = user.getRoles().stream().map(AuthRole::new).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Principal getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String getName() {
        return this.principal.name();
    }

    public record Principal(Long userId, String name) {
    }

    public static class AuthRole implements GrantedAuthority {

        private final String authority;

        public AuthRole(Role role) {
            this.authority = role.getName();
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
    }
}
