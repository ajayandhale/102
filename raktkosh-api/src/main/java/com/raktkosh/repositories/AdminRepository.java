package com.raktkosh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raktkosh.core.Role;
import com.raktkosh.pojos.User;

public interface AdminRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	List<User> findByRole(Role role);

	Optional<User> findById(Long id);

	List<User> findByActivated(boolean activated);

}
