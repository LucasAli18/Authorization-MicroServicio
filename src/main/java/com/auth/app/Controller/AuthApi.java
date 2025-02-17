package com.auth.app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.auth.app.commons.constants.ApiPathConstants;
import com.auth.app.commons.dto.TokenResponse;
import com.auth.app.commons.dto.UserRequest;

import jakarta.validation.Valid;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

	@PostMapping(value="/register")
	ResponseEntity<TokenResponse> createUser(@RequestBody @Valid UserRequest userRequest);
	@GetMapping
	ResponseEntity<String> getUser(@RequestAttribute(name = "X-User-Id") String userId);
}
