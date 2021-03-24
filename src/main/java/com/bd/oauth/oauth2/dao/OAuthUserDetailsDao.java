package com.bd.oauth.oauth2.dao;

import com.bd.oauth.oauth2.model.OAuthUserDetails;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("oauthUserDetailsDao")
public class OAuthUserDetailsDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public OAuthUserDetails getUserById(String username) {
        return sqlSession.selectOne("user.selectUserById", username);
    }
}
