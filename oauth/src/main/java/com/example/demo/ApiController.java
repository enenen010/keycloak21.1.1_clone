package com.example.demo;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/list")
	public List list(Principal principal,Authentication auth) {
		System.out.println(principal);
		System.out.println(auth);
		
		return Arrays.asList("test1","test2","test3");
	}
}
