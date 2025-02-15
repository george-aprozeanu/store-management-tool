package com.aprozeanu.smt.auth.configuration;

import com.aprozeanu.smt.auth.model.Role;
import com.aprozeanu.smt.auth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthenticationImpl implements Authentication {
    private final Principal principal;
    private final List<GrantedAuthority> roles;

    public AuthenticationImpl(User user) {
        this.principal = new Principal(user.getUserId(), user.getUsername());
        this.roles = user.getRoles().stream().map(AuthenticationImpl::roleToGrantedAuthority).toList();
    }

    static GrantedAuthority roleToGrantedAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
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
}
