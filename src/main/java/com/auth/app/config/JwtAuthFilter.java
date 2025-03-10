package com.auth.app.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth.app.Repository.UserRepository;
import com.auth.app.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserRepository userRepository;
	
	public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
		this.jwtService = jwtService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Optional.ofNullable(request.getHeader("Authorization"))
		.filter(header -> !header.isBlank())
		.map(header -> header.substring(7))
		.map(jwtService::extractUserId)
		.flatMap(userId -> userRepository.findById(Long.valueOf(userId)))
		.ifPresent( userDetails -> {
				request.setAttribute("X-User-Id", userDetails.getId());
				processAuthentication(request, userDetails);
				
		});
		
		filterChain.doFilter(request, response);
	}

	private void processAuthentication(HttpServletRequest request, UserDetails userDetails) {
		String jwtToken = request.getHeader("Authorization").substring(7);
		Optional.of(jwtToken)
			.filter(token -> !jwtService.isExpired(token))
			.ifPresent(token -> {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities()
			);
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			});
	}

}
