package com.auth.app.Service;

import com.auth.app.commons.dto.TokenResponse;

import io.jsonwebtoken.Claims;

public interface JwtService {

	TokenResponse generateToken(Long userId);
	Claims getClaims(String token);
	boolean isExpired(String token);
	Integer extractUserId(String token);
}
