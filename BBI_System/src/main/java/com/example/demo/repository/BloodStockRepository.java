package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.BloodBank;
import com.example.demo.domain.BloodStock;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {
	
	List<BloodStock> findAllByBloodBank(BloodBank bloodBanks);
}
