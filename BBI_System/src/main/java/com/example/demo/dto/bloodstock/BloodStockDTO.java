package com.example.demo.dto.bloodstock;

import java.time.LocalDateTime;

import com.example.demo.utility.ValidationGroups.Create;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.validation.constraints.NotNull;

public record BloodStockDTO(
		Long bloodStockId,
		@NotNull(groups = Create.class) Long quantity,
		@NotNull(groups = Create.class) LocalDateTime bestBeforeDate,
		@NotNull(groups = Create.class) String bloodGroup,
		@NotNull(groups = Create.class) String status,
		@NotNull(groups = Create.class) String serialNo) {
}
