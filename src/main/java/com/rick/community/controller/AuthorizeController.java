package com.rick.community.controller;


import com.rick.community.dto.AccessTokenDto;
import com.rick.community.dto.GithubUser;
import com.rick.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("github.client.secret")
    private String clientSecret;
    @Value("github.redirect.uri")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("e2e0eb7f99a1c266e7e3");
        accessTokenDto.setClient_secret("6d13b3a980c4f08a9ff94791b2f5b89b1be7244d");
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri("http://localhost:8888/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }
}
