package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BloodBank;
import com.example.demo.domain.BloodGroup;
import com.example.demo.domain.BloodStock;
import com.example.demo.domain.BloodStockStatus;
import com.example.demo.domain.User;
import com.example.demo.dto.bloodbank.BloodBankDTO;
import com.example.demo.dto.bloodbank.BloodBankInfoAndStocksDTO;
import com.example.demo.dto.bloodstock.BloodStockAndBankDTO;
import com.example.demo.dto.bloodstock.BloodStockDTO;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.BloodStockRepository;

import jakarta.transaction.Transactional;

@Service
public class BloodStockService {
	
	private final UserService userService;
	private final BloodBankRepository bloodBankRepository;
	private final BloodStockRepository bloodStockRepository;
	
	public BloodStockService(UserService userService, BloodBankRepository bloodBankRepository2, BloodStockRepository bloodStockRepository) {
		this.userService = userService;
		this.bloodBankRepository = bloodBankRepository2;
		this.bloodStockRepository = bloodStockRepository;
		
	}
	
	public List<BloodStockAndBankDTO> getAllBloodStock() {
		
		List<BloodStock> stocks = this.bloodStockRepository.findAll();
		List<BloodStockAndBankDTO> stocksAndBloodBankDTO = new ArrayList<>();
		
		for(BloodStock stock : stocks) {
			
			BloodBank currentBloodBank = stock.getBloodBank();
			
			BloodBankDTO bloodBankInfo = new BloodBankDTO(currentBloodBank.getBloodBankId(),
					currentBloodBank.getBloodBankName(),
					currentBloodBank.getAddress(),
					currentBloodBank.getCity(),
					currentBloodBank.getPhone(),
					currentBloodBank.getWebsite(),
					currentBloodBank.getEmail());
					
			BloodStockAndBankDTO dto = new BloodStockAndBankDTO(stock.getBloodStockId(),
						stock.getQuantity(),
						stock.getBestBeforeDate(),
						stock.getBloodGroup().toString(),
						stock.getStatus().toString(),
						stock.getSerialNo(),
						bloodBankInfo
					);
			
			stocksAndBloodBankDTO.add(dto);
		}
		
		return stocksAndBloodBankDTO;
	}
	
	@Transactional
	public BloodStockAndBankDTO getBloodStockById(Long bloodStockId) throws Exception { 
		
		Optional<BloodStock> existingResult = this.bloodStockRepository.findById(bloodStockId);
		
		if(existingResult.isPresent()) {
			
			BloodStock stock = existingResult.get();
			
			BloodBank currentBloodBank = stock.getBloodBank();
			
			BloodBankDTO bloodBankInfo = new BloodBankDTO(currentBloodBank.getBloodBankId(),
					currentBloodBank.getBloodBankName(),
					currentBloodBank.getAddress(),
					currentBloodBank.getCity(),
					currentBloodBank.getPhone(),
					currentBloodBank.getWebsite(),
					currentBloodBank.getEmail());
					
			return new BloodStockAndBankDTO(stock.getBloodStockId(),
						stock.getQuantity(),
						stock.getBestBeforeDate(),
						stock.getBloodGroup().toString(),
						stock.getStatus().toString(),
						stock.getSerialNo(),
						bloodBankInfo
					);
			
		} else {
			return null;
		}
		
	}
	
	@Transactional
	public void createBloodBank(BloodStockDTO dto, String username) throws Exception {
		
		Optional<User> currentUser = this.userService.getUserByUsername(username);
		
		if(currentUser.isPresent() ) {
			
			Optional<BloodBank> bloodBank = this.bloodBankRepository.findByUser(currentUser.get());
			
			if(bloodBank.isEmpty()) {
				throw new Exception("Blood Bank cannot be found.");
			}
			
			BloodStock newStock = new BloodStock();
			
			newStock.setBloodBank(bloodBank.get());
			newStock.setQuantity(dto.quantity());
			newStock.setBloodGroup(BloodGroup.valueOf(dto.bloodGroup()));
			newStock.setStatus(BloodStockStatus.valueOf(dto.status()));
			newStock.setBestBeforeDate(dto.bestBeforeDate());
			newStock.setSerialNo(dto.serialNo());
			
			this.bloodStockRepository.save(newStock);
			
		} else {
			throw new Exception("User is not found."); 
		}
	}
	
	@Transactional
	public void updateBloodStock(Long bloodStockId, BloodStockDTO dto, String username) throws Exception {
		
		Optional<BloodStock> existingInfo = this.bloodStockRepository.findById(bloodStockId);
			
		if(existingInfo.isPresent()) {
			
			BloodStock existing = existingInfo.get();
			
			if(dto.quantity() != null ) {
				existing.setQuantity(dto.quantity());
			}
			if(dto.bestBeforeDate() != null) {
				existing.setBestBeforeDate(dto.bestBeforeDate());
			}
			if(dto.bloodGroup() != null) {
				existing.setBloodGroup(BloodGroup.valueOf(dto.bloodGroup()));
			}
			if(dto.status() != null) {
				existing.setStatus(BloodStockStatus.valueOf(dto.status()));
			}
			if(dto.serialNo() != null) {
				existing.setSerialNo(dto.serialNo());
			}
			
			this.bloodStockRepository.save(existing);
			
		} else {
			throw new Exception("Blood Stock is not found."); 
		}
	}
	
	@Transactional
	public void deleteBloodBankById(Long existingId, String username) throws Exception {
		
		Optional<BloodStock> existingInfo = this.bloodStockRepository.findById(existingId);
		
		if(existingInfo.isPresent()) {
			
			this.bloodStockRepository.deleteById(existingId);
		}
	}
}
