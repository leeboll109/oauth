package com.bd.oauth.oauth2.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class OAuthUserDetails implements UserDetails {

    private int msrl;
    private String username;
    private String password;
    private String authority;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private String name;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(authority));
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("OAuthUserDetails [")
                .append("username=").append(username)
                .append(", password=").append(password)
                .append(", authority=").append(authority)
                .append(", enabled=").append(enabled)
                .append(", accountNonExpired=").append(accountNonExpired)
                .append(", accountNonLocked=").append(accountNonLocked)
                .append(", credentialsNonExpired=").append(credentialsNonExpired)
                .append(", name=").append(name)
                .append("]");
        return builder.toString();
    }
}
