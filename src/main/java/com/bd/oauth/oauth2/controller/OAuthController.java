package com.bd.oauth.oauth2.controller;

import com.bd.oauth.oauth2.model.OAuthClientDetails;
import com.bd.oauth.oauth2.service.OAuthClientDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;


@Controller
@RequestMapping("/api")
public class OAuthController {
    private static final Logger log = LoggerFactory.getLogger(OAuthController.class);

    @Autowired
    private OAuthClientDetailsService oAuthClientDetailsService;

    @PostMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/client")
    @ResponseBody
    public ClientDetails getClient(@RequestParam("username") String username) {
        ClientDetails client = oAuthClientDetailsService.loadClientByClientId(username);
        return client;
    }

    @GetMapping("/callback")
    public ModelAndView showTokenAndClient(@RequestParam("code") String code) throws IOException {
        ResponseEntity<String> response = null;
        log.info("Authorization Code ===========> {}", code);

        RestTemplate restTemplate = new RestTemplate();

        String credentials = "client:123456";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept((Arrays.asList(MediaType.APPLICATION_JSON)));
        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "http://localhost:8080/oauth/token";
        access_token_url += "?code=" + code;
        access_token_url += "&grant_type=authorization_code";
        access_token_url += "&redirect_uri=http://localhost:8080/api/callback";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        log.info("Access Token Response ============> {}", response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
        String token = node.path("access_token").asText();

        String url = "http://localhost:8080/api/client?username=client";

        HttpHeaders headers1 = new HttpHeaders();
        headers1.setAccept(Arrays.asList(MediaType.ALL));
        headers1.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers1);
        log.info("headers1 ======> {}", headers1);

        ResponseEntity<String> clientDetail = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        log.info("ClientDetails ======> {}", clientDetail);
        String client = clientDetail.getBody();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("client", client);
        return mav;
    }
}
