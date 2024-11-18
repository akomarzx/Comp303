/**
 * 
 */
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.BloodBank;
import com.example.demo.domain.User;

/**
 * 
 */
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
	Optional<BloodBank> findByUser(User ownerUser);
}
