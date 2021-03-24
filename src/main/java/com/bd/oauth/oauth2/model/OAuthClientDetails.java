package com.bd.oauth.oauth2.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Getter
public class OAuthClientDetails implements ClientDetails {
    private int id;
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private boolean autoapprove;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if(resourceIds == null ) return null;
        String[] s = resourceIds.split(",");
        return new HashSet<>(Arrays.asList(s));
    }

    @Override
    public boolean isSecretRequired() {
        return clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return scope != null;
    }

    @Override
    public Set<String> getScope() {
        if(scope == null) return null;
        String[] s = scope.split(",");
        return new HashSet<>(Arrays.asList(s));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if( authorizedGrantTypes == null ) return null;
        String[] s = authorizedGrantTypes.split(",");
        return new HashSet<>(Arrays.asList(s));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if( webServerRedirectUri == null ) return null;
        String[] s = webServerRedirectUri.split(",");
        return new HashSet<>(Arrays.asList(s));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if(authorities == null ) return new ArrayList<>();
        return AuthorityUtils.createAuthorityList(authorities.split(","));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoapprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("OAuthClientDetails [")
                .append("clientId=").append(clientId)
                .append(", clientSecret=").append(clientSecret)
                .append(", scope=").append(scope)
                .append(", resourceIds=").append(resourceIds)
                .append(", authorizedGrantTypes=").append(authorizedGrantTypes)
                .append(", authorities=").append(authorities)
                .append(", redirectUris=").append(webServerRedirectUri)
                .append(", accessTokenValidity=").append(accessTokenValidity)
                .append(", refreshTokenValidity=").append(refreshTokenValidity)
                .append(", additionalInformation=").append(additionalInformation)
                .append("]");
        return builder.toString();
    }
}
