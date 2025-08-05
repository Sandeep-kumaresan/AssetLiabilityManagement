package com.oracle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DebtDTO {

    @Schema(description = "Unique identifier for the liability", defaultValue = "0")
    private Long liabilityId;

    @Schema(description = "Customer ID associated with the debt", defaultValue = "0")
    @NotNull(message = "{com.oracle.dto.DebtDTO.customerId.required}")
    private Long customerId;

    @Schema(description = "Principal amount of the debt", defaultValue = "50000.00")
    @NotNull(message = "{com.oracle.dto.DebtDTO.amount.required}")
    @Positive(message = "{com.oracle.dto.DebtDTO.amount.positive}")
    private BigDecimal amount;

    @Schema(description = "Annual interest rate in percentage", defaultValue = "7.5")
    @NotNull(message = "{com.oracle.dto.DebtDTO.interestRate.required}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{com.oracle.dto.DebtDTO.interestRate.positive}")
    private Double interestRate;

    @Schema(description = "Currency code for the debt", defaultValue = "INR")
    @NotNull(message = "{com.oracle.dto.DebtDTO.currencyCode.required}")
    @Size(min = 3, max = 3, message = "{com.oracle.dto.DebtDTO.currencyCode.size}")
    private String currencyCode;

    @Schema(description = "Maturity date of the debt")
    @NotNull(message = "{com.oracle.dto.DebtDTO.maturityDate.required}")
    private LocalDate maturityDate;

    @Schema(description = "Type of collateral backing the debt", defaultValue = "PROPERTY")
    @NotBlank(message = "{com.oracle.dto.DebtDTO.collateralType.required}")
    private String collateralType;

    @Schema(description = "Repayment schedule description", defaultValue = "MONTHLY")
    @NotBlank(message = "{com.oracle.dto.DebtDTO.repaymentSchedule.required}")
    private String repaymentSchedule;
}