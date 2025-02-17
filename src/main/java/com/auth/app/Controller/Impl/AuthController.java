package com.auth.app.Controller.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.auth.app.Controller.AuthApi;
import com.auth.app.Service.AuthService;
import com.auth.app.commons.dto.TokenResponse;
import com.auth.app.commons.dto.UserRequest;
import jakarta.validation.Valid;

@RestController
public class AuthController implements AuthApi{

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public ResponseEntity<TokenResponse> createUser(@Valid UserRequest userRequest) {
		return ResponseEntity.ok(authService.createUser(userRequest));
	}

	@Override
	public ResponseEntity<String> getUser(String userId) {
		return ResponseEntity.ok(userId);
	}
	
	

}
