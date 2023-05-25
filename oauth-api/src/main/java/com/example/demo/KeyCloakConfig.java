package com.example.demo;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {

	@Bean
	KeycloakSpringBootConfigResolver resolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
//	@Bean
//	public Keycloak keycloak() {
//		return KeycloakBuilder.builder()
//				.serverUrl("http://localhost:8082")
//				.realm("hello-realm")
//				.clientId("hello-client")
//				.clientSecret("BqF4KumpFU8XyhuS4LKjv8zU8wjUjJhh")
//				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//				.build();
//	}
}
