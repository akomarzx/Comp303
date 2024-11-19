package com.example.demo.dto.bloodstock;

import java.time.LocalDateTime;

import com.example.demo.dto.bloodbank.BloodBankDTO;

public record BloodStockAndBankDTO(
		Long bloodStockId,
		Long quantity,
		LocalDateTime bestBeforeDate,
		String bloodGroup,
		String status,
		String serialNo,
		BloodBankDTO bloodBank){}
