package com.auth.app.Service.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth.app.Repository.UserRepository;
import com.auth.app.Service.AuthService;
import com.auth.app.Service.JwtService;
import com.auth.app.commons.dto.TokenResponse;
import com.auth.app.commons.dto.UserRequest;
import com.auth.app.commons.entities.UserModel;

@Service
public class AuthServiceImpl implements AuthService{
	private final UserRepository userRepository;
	private final JwtService jwtService;
	
	public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}
	
	@Override
	public TokenResponse createUser(UserRequest userRequest) {
		return Optional.of(userRequest)
				.map(this::mapToEntity)
				.map(userRepository::save)
				.map(userCreated -> jwtService.generateToken(userCreated.getId()))
				.orElseThrow(()-> new RuntimeException("Error creating user"));
	} 
	
	private UserModel mapToEntity(UserRequest userRequest) {
		return UserModel.builder()
				.email(userRequest.getEmail())
				.password(userRequest.getPassword())
				.role("USER")
				.build();
	}

}
