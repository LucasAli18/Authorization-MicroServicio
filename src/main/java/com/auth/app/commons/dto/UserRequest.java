package com.auth.app.commons.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {

	@NotNull
	private String email;
	@NotNull
	private String password;
	
}
