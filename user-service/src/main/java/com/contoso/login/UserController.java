package com.contoso.login;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@RequestMapping("/user")
	public Principal user(Principal user,HttpServletRequest request) {
		// /user === http://192.168.99.100:8181/uaa/user or http://localhost:8181/uaa/user
		System.out.println("/user === "+request.getRequestURL().toString());
		System.out.println(user);
		return user;
	}
}
