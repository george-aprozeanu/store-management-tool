package com.aprozeanu.smt.auth.configuration;

import com.aprozeanu.smt.auth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthenticationImpl implements Authentication {
    private final User user;
    private final List<SimpleGrantedAuthority> roles;

    public AuthenticationImpl(User user) {
        this.user = user;
        this.roles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getCredentials() {
        return this.user.getPassword();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public User getPrincipal() {
        return this.user;
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
        return this.user.getUsername();
    }
}
