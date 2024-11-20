package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponseEntity;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.bloodbank.BloodBankDTO;
import com.example.demo.dto.bloodbank.BloodBankInfoAndStocksDTO;
import com.example.demo.dto.bloodstock.BloodStockAndBankDTO;
import com.example.demo.dto.bloodstock.BloodStockDTO;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.BloodStockService;
import com.example.demo.utility.ValidationGroups.Create;
import com.example.demo.utility.ValidationGroups.Update;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@RestController
@RequestMapping("/bloodstock")
public class BloodStockController {
	
	private final BloodStockService bloodStockService;
	
	public BloodStockController(BloodStockService bloodBankService) {
		this.bloodStockService = bloodBankService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponseEntity<List<BloodStockAndBankDTO>>> getAllBloodBanks(@RequestParam(name = "type", required = false) String bloodType) {
		List<BloodStockAndBankDTO> bloodBanks = this.bloodStockService.getAllBloodStock(bloodType);
		ApiResponseEntity<List<BloodStockAndBankDTO>> response = new ApiResponseEntity<>(bloodBanks, bloodBanks.size(),"");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseEntity<BloodStockAndBankDTO>> getBloodBank(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		BloodStockAndBankDTO bloodBankInfo;
		try {
			bloodBankInfo = this.bloodStockService.getBloodStockById(id);
			ApiResponseEntity<BloodStockAndBankDTO> response = new ApiResponseEntity<>(bloodBankInfo, 1, "");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponseEntity<BloodStockAndBankDTO> response = new ApiResponseEntity<>(null, null, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> createBloodBank(@Validated(Create.class) @RequestBody BloodStockDTO upsertBloodStockDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.bloodStockService.createBloodBank(upsertBloodStockDTO, userDetails.getUsername());
			return new ResponseEntity<>(new MessageResponse("Blood Stock Successfully Created"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	ResponseEntity<MessageResponse> updateBloodBanks(@PathVariable Long id, @Validated(Update.class) @RequestBody BloodStockDTO upsertBloodStockDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.bloodStockService.updateBloodStock(id, upsertBloodStockDTO, userDetails.getUsername());
			return new ResponseEntity<>(new MessageResponse("Blood Stock info Successfully Updated"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteBloodBank(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.bloodStockService.deleteBloodBankById(id, userDetails.getUsername());
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
