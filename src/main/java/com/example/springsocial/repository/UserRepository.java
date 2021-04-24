package com.example.springsocial.repository;

import com.example.springsocial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * find by mail
	 * @param email
	 * @return
	 */
    Optional<User> findByEmail(String email);

    /**
     * validate if exist mail
     * @param email
     * @return
     */
    Boolean existsByEmail(String email);

}
