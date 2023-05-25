package com.example.demo;

import java.security.Principal;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@PreAuthorize("permitAll()")
	@GetMapping("/")
	public String home(Principal principal,Authentication auth) {
		System.out.println(principal);
		System.out.println(auth);
		SecurityContext ctxt = SecurityContextHolder.getContext();
		System.out.println(ctxt);
		System.out.println(ctxt.getAuthentication());
		if(principal==null)
			return "아무나";		
		return principal.getName();
	}
	
	@GetMapping("/login")
	public String all() {
		return "<a href='http://localhost:8082/realms/bit-realm/protocol/openid-connect/auth?response_type=code&client_id=bit-client'>login</a>";
	}
}
