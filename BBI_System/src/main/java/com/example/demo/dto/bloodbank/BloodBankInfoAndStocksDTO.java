package com.example.demo.dto.bloodbank;

import java.util.List;

import com.example.demo.domain.BloodStock;
import com.example.demo.dto.bloodstock.BloodStockDTO;

public record BloodBankInfoAndStocksDTO(BloodBankDTO bloodBankInfo, List<BloodStockDTO> bloodStocks) {
}
