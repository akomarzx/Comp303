package com.example.demo.dto.bloodbank;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
import com.example.demo.utility.ValidationGroups.Create;

import jakarta.validation.constraints.NotNull;

public record BloodBankDTO(	Long bloodBankId,
							@NotNull(groups = Create.class) String bloodBankName,
							@NotNull(groups = Create.class) String address,
							@NotNull(groups = Create.class) String city,
							@NotNull(groups = Create.class) String phone,
							@NotNull(groups = Create.class) String website,
							@NotNull(groups = Create.class) String email){}
