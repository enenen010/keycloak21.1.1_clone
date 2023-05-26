package com.example.demo;

import java.util.Arrays;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;


@Log4j2
@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "hello";
	}
	
	@GetMapping("/test")
	public String check() {
		return "check";
	}
	
	@Value("${keycloak.auth-server-url}")
	String serverUrl;
	@Value("${keycloak.realm}")
	String realm;
	@Value("${keycloak.resource}")
	String clientID;
	@Value("${keycloak.credentials.secret}")
	String secret;
	
	@GetMapping("/user")
	public ResponseEntity<?> check(String id,String pw) {
		String url="http://localhost:8082/realms/"+realm+"/protocol/openid-connect/token";
		RestTemplate template=new RestTemplate();
		LinkedMultiValueMap<String, String> param=new LinkedMultiValueMap<>();
		param.add("client_id", clientID);
		param.add("grant_type", "password");
		param.add("username", id);
		param.add("password", pw);
		param.add("client_secret", secret);
		System.out.println(secret);
		return template.postForEntity(url, param, Map.class);
	}
	
	@GetMapping("/add")
	public ResponseEntity<?> add(String id,String pw) {
		
		//사용자 정보객체 만들어 id와 설정지정
		UserRepresentation userRep=new UserRepresentation();
		userRep.setUsername(id);
		userRep.setEnabled(true);
		
		//Credential 정보 객체 만들어 패스워드 등 설정해 사용자 정보에 추가
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);//비번바꾸기
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(pw);
		userRep.setCredentials(Arrays.asList(passwordCred));
		
		//url, realm 등으로 특정한 대상 키클록 객체에 유저 세팅
		//user02계정에 권한 있어야 함 
		Keycloak keycloak = Keycloak.getInstance(serverUrl,realm,"user02","1234",clientID,secret);
//		Keycloak keycloak = Keycloak.getInstance(serverUrl,realm,clientID,tokenString);
		UsersResource usersRes=keycloak.realm(realm).users();
		Response resp = usersRes.create(userRep);
		log.debug(resp.getStatus());
		
		//유저에 권한 추가 
		String userID = CreatedResponseUtil.getCreatedId(resp);
		UserResource userRes = usersRes.get(userID);
		RealmResource realmRes = keycloak.realm(realm);
		ClientRepresentation clientRep=realmRes.clients().findByClientId(clientID).get(0);
		RoleRepresentation clientRoleRep = //해당 client에 권한 있어야 가능(USER,ADMIN) => 권한 USER에 대한 정보 가져옴
				realmRes.clients().get(clientRep.getId()).roles().get("USER").toRepresentation();
		System.out.println(clientRep.getId());
		userRes.roles().clientLevel(clientRep.getId()).add(Arrays.asList(clientRoleRep));
		
		return ResponseEntity.status(resp.getStatus()).build();
	}
	
}
