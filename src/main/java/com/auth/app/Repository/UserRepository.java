package com.auth.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.app.commons.entities.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
	Optional<UserModel> findByEmail(String email);
}
