package com.example.demo;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Bean
//	Keycloak keycloak() {
//		return KeycloakBuilder.builder()
//				.serverUrl("http://localhost:8082")
//				.realm("bit-realm")
//				.clientId("bit-client")
//				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//				.clientSecret("BqF4KumpFU8XyhuS4LKjv8zU8wjUjJhh")
//				.build();
//	}
	
	@Bean
	KeycloakSpringBootConfigResolver resolver() {
		return new KeycloakSpringBootConfigResolver();
	}

}
