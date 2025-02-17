package com.auth.app.Service;

import com.auth.app.commons.dto.TokenResponse;
import com.auth.app.commons.dto.UserRequest;

public interface AuthService {

	TokenResponse createUser(UserRequest userRequest);
	
}
