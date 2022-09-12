package com.raktkosh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raktkosh.pojos.User;
import com.raktkosh.pojos.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long>{

	
}
