package com.example.demo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	
	@GetMapping("/list")
	public List<?> check() {
		return List.of(
				"item1"
				,"item2"
				,"item3"
				);
	}
}

