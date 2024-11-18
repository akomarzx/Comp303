package com.example.demo.dto.bloodbank;

import java.util.List;

import com.example.demo.domain.BloodStock;

public record BloodBankInfoAndStocksDTO(BloodBankDTO bloodBankInfo, List<BloodStock> bloodStocks) {
}
