package com.raktkosh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raktkosh.pojos.BloodBank;

@Repository
public interface BankRepository extends JpaRepository<BloodBank, Long> {

	Optional<BloodBank> findByName(String bankName);
}
