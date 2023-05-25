package com.example.demo;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping({"/","index"})
    public LinkedHashMap getIndex(@AuthenticationPrincipal OAuth2User principal, OAuth2AuthenticationToken authentication) {
    	LinkedHashMap map=new LinkedHashMap();
    	System.out.println(principal);
    	System.out.println(authentication);
        //OAuth2AuthorizedClient authorizedClient = this.getAuthorizedClient(authentication);
        //System.out.println(authorizedClient);
        map.put("title", "index page");
        map.put("API", "http://localhost:8080/api/list");
        map.put("logout", "http://localhost:8080/logout");
        if(authentication!=null)
        map.put("id_token_hint", authentication.getPrincipal().getAttribute("id_token_hint"));
        if(authentication!=null)
        	map.put("userName", authentication.getPrincipal().getAttribute("preferred_username"));
        if(authentication!=null)
        map.put("Name", authentication.getPrincipal().getAttributes());
//        map.put("clientName",authorizedClient.getClientRegistration().getClientId());
//        map.put("scopes",authorizedClient.getAccessToken().getScopes());
        return map;

    }

    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
        System.out.println("1."+authentication.getAuthorizedClientRegistrationId());
        System.out.println("2."+authentication.getName());
    	return this.authorizedClientService.loadAuthorizedClient(
            authentication.getAuthorizedClientRegistrationId(), authentication.getName());
    }
	
}
