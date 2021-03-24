package com.bd.oauth;

import com.bd.oauth.oauth2.util.PrintKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthApplication {


    public static void main(String[] args) {
        //PrintKeyPair printKeyPair = new PrintKeyPair();
        //printKeyPair.printKeypair();

        SpringApplication.run(OauthApplication.class, args);
    }

}
