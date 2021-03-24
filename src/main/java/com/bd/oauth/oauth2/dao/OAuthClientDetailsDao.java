package com.bd.oauth.oauth2.dao;

import com.bd.oauth.oauth2.model.OAuthClientDetails;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("oauthClientDetailsDao")
public class OAuthClientDetailsDao {
    @Autowired
    private SqlSessionTemplate sqlSession;

    public OAuthClientDetails getClientById(String username) {
        return sqlSession.selectOne("client.selectClientById", username);
    }
}
