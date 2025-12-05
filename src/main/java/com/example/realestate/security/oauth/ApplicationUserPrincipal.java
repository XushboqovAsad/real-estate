package com.example.realestate.security.oauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ApplicationUserPrincipal implements OAuth2User {

    private final UUID id;
    private final OAuth2User oAuth2User;
    private final Collection<? extends GrantedAuthority> authorities;

    public ApplicationUserPrincipal(UUID id, OAuth2User oAuth2User, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.oAuth2User = oAuth2User;
        this.authorities = authorities;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return (String) oAuth2User.getAttribute("name");
    }
}
