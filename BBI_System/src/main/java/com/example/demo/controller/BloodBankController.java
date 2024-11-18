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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponseEntity;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.bloodbank.BloodBankDTO;
import com.example.demo.dto.bloodbank.BloodBankInfoAndStocksDTO;
import com.example.demo.service.BloodBankService;
import com.example.demo.utility.ValidationGroups.Create;

@RestController
@RequestMapping("/bloodbank")
public class BloodBankController {
	
	private final BloodBankService bloodBankService;
	
	public BloodBankController(BloodBankService bloodBankService) {
		this.bloodBankService = bloodBankService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponseEntity<List<BloodBankDTO>>> getAllBloodBanks() {
		List<BloodBankDTO> bloodBanks = this.bloodBankService.getAllBloodBanks();
		ApiResponseEntity<List<BloodBankDTO>> response = new ApiResponseEntity<>(bloodBanks, "");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseEntity<BloodBankInfoAndStocksDTO>> getBloodBank(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		BloodBankInfoAndStocksDTO bloodBankInfo;
		try {
			bloodBankInfo = this.bloodBankService.getBloodBankById(id);
			ApiResponseEntity<BloodBankInfoAndStocksDTO> response = new ApiResponseEntity<>(bloodBankInfo, "");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponseEntity<BloodBankInfoAndStocksDTO> response = new ApiResponseEntity<>(null, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> createBloodBank(@Validated(Create.class) @RequestBody BloodBankDTO upsertBloodBanktDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.bloodBankService.createBloodBank(upsertBloodBanktDTO, userDetails.getUsername());
			return new ResponseEntity<>(new MessageResponse("Blood Bank Successfully Created"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	ResponseEntity<MessageResponse> updateBloodBanks(@PathVariable Long id, @Validated(Create.class) @RequestBody BloodBankDTO upsertBloodBanktDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.bloodBankService.updateBloodBank(id, upsertBloodBanktDTO, userDetails.getUsername());
			return new ResponseEntity<>(new MessageResponse("Blood Bank info Successfully Updated"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteBloodBank(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.bloodBankService.deleteBloodBankById(id, userDetails.getUsername());
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
