package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class LogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse response, Authentication authentication)
			throws IOException {
		super.onLogoutSuccess(req, response, authentication);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
	    String realmName = "bit-realm";
	    String url = "http://localhost:8082/auth/realms/" + realmName + "/protocol/openid-connect/logout";

	    RestTemplate restTemplate=new RestTemplate();
		restTemplate.postForEntity(url, request, Object.class);
	}
}
