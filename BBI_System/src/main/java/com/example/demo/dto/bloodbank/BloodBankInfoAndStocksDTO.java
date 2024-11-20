package com.example.demo.dto.bloodbank;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
import java.util.List;

import com.example.demo.domain.BloodStock;
import com.example.demo.dto.bloodstock.BloodStockDTO;

public record BloodBankInfoAndStocksDTO(BloodBankDTO bloodBankInfo, List<BloodStockDTO> bloodStocks) {
}
