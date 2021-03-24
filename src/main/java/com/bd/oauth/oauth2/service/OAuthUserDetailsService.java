package com.bd.oauth.oauth2.service;

import com.bd.oauth.oauth2.dao.OAuthUserDetailsDao;
import com.bd.oauth.oauth2.model.OAuthUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("oauthUserDetailsService")
public class OAuthUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(OAuthUserDetailsService.class);

    @Autowired
    @Qualifier("oauthUserDetailsDao")
    OAuthUserDetailsDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OAuthUserDetails user = dao.getUserById(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        log.debug("User : {}", user.toString());
        return user;
    }
}
