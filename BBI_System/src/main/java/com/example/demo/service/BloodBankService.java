package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BloodBank;
import com.example.demo.domain.BloodGroup;
import com.example.demo.domain.BloodStock;
import com.example.demo.domain.User;
import com.example.demo.dto.bloodbank.BloodBankDTO;
import com.example.demo.dto.bloodbank.BloodBankInfoAndStocksDTO;
import com.example.demo.dto.bloodstock.BloodStockDTO;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.BloodStockRepository;
import com.example.demo.repository.PatientRepository;

import jakarta.transaction.Transactional;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Service
public class BloodBankService {
	
	private final UserService userService;
	private final BloodBankRepository bloodBankRepository;
	private final BloodStockRepository stockRepository;
	
	public BloodBankService(BloodBankRepository bloodBankRepository, UserService userService, BloodBankRepository bloodBankRepository2, BloodStockRepository stockRepository, BloodStockRepository stockRepository2) {
		this.userService = userService;
		this.bloodBankRepository = bloodBankRepository2;
		this.stockRepository = stockRepository2;
	}
	
	public List<BloodBankDTO> getAllBloodBanks() {
		List<BloodBank> allBbs = this.bloodBankRepository.findAll();
		List<BloodBankDTO> mappedBbs = new ArrayList<>();
		
		for(BloodBank bloodBank : allBbs) {
			BloodBankDTO dto = new BloodBankDTO(bloodBank.getBloodBankId(),
						bloodBank.getBloodBankName(),
						bloodBank.getAddress(),
						bloodBank.getCity(),
						bloodBank.getPhone(),
						bloodBank.getWebsite(),
						bloodBank.getEmail()
					);
			
			mappedBbs.add(dto);
		}
		
		return mappedBbs;
	}
	
	@Transactional
	public BloodBankInfoAndStocksDTO getBloodBankById(Long bloodBankId) throws Exception { 
		
		Optional<BloodBank> existingResult = this.bloodBankRepository.findById(bloodBankId);
		
		if(existingResult.isPresent()) {
			
			BloodBank bloodBank = existingResult.get();
			
			List<BloodStock> bloodBankStocks = this.stockRepository.findAllByBloodBank(bloodBank);
					
			BloodBankDTO dto = new BloodBankDTO(bloodBank.getBloodBankId(),
					bloodBank.getBloodBankName(),
					bloodBank.getAddress(),
					bloodBank.getCity(),
					bloodBank.getPhone(),
					bloodBank.getWebsite(),
					bloodBank.getEmail()
				);
			
			List<BloodStockDTO> bloodStockDTO = new ArrayList<>();
			
			for(BloodStock stock : bloodBankStocks) {
				bloodStockDTO.add(new BloodStockDTO(
						stock.getBloodStockId(),
						stock.getQuantity(),
						stock.getBestBeforeDate(),
						stock.getBloodGroup().toString(),
						stock.getStatus().toString(),
						stock.getSerialNo()
				));
			}
			
			return new BloodBankInfoAndStocksDTO(dto, bloodStockDTO);
			
		} else {
			return null;
		}
		
	}
	
	@Transactional
	public void createBloodBank(BloodBankDTO dto, String username) throws Exception {
		
		Optional<User> currentUser = this.userService.getUserByUsername(username);
		
		if(currentUser.isPresent() ) {
			
			Optional<BloodBank> existingInfo = this.bloodBankRepository.findByUser(currentUser.get());
			
			if(existingInfo.isPresent()) {
				throw new Exception("Blood Bank info already exist.");
			}
			
			BloodBank newBloodBank = new BloodBank();
			newBloodBank.setBloodBankName(dto.bloodBankName());;
			newBloodBank.setAddress(dto.address());
			newBloodBank.setCity(dto.city());
			newBloodBank.setPhone(dto.phone());
			newBloodBank.setEmail(dto.email());
			newBloodBank.setUser(currentUser.get());
			newBloodBank.setWebsite(dto.website());
			
			this.bloodBankRepository.save(newBloodBank);
			
		} else {
			throw new Exception("User is not found."); 
		}
	}
	
	@Transactional
	public void updateBloodBank(Long bloodBankId, BloodBankDTO dto, String username) throws Exception {
		
		Optional<BloodBank> existingInfo = this.bloodBankRepository.findById(bloodBankId);
			
		if(existingInfo.isPresent()) {
			
			if(!existingInfo.get().getUser().getUsername().equals(username)) {
				throw new Exception("BloodBank Info does not belong to user.");
			}
			
			BloodBank existing = existingInfo.get();
			
			if(dto.bloodBankName() != null ) {
				existing.setBloodBankName(dto.bloodBankName());
			}
			if(dto.address() != null) {
				existing.setAddress(dto.address());
			}
			if(dto.city() != null) {
				existing.setCity(dto.city());
			}
			if(dto.website() != null) {
				existing.setWebsite(dto.website());
			}
			if(dto.phone() != null) {
				existing.setPhone(dto.phone());
			}
			if(dto.email() != null) {
				existing.setEmail(dto.email());	
			}
			
			this.bloodBankRepository.save(existing);
			
		} else {
			throw new Exception("Blood Bank is not found."); 
		}
	}
	
	@Transactional
	public void deleteBloodBankById(Long existingId, String username) throws Exception {
		
		Optional<BloodBank> existingInfo = this.bloodBankRepository.findById(existingId);
		
		if(existingInfo.isPresent()) {
			
			if(!existingInfo.get().getUser().getUsername().equals(username)) {
				throw new Exception("Blood Bank Info does not belong to user.");
			}
			
			this.bloodBankRepository.deleteById(existingId);
		}
	}
}
