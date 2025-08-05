package com.oracle.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.oracle.enums.FlowStatus;
import com.oracle.enums.FlowType;
import com.oracle.enums.SourceType;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CashFlowDTO {
	@Schema(description = "Unique identifier", defaultValue = "0")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.sourceId.required}")
    private UUID sourceId;

    @Schema(description = "Type of source: ASSET or LIABILITY", defaultValue = "ASSET")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.sourceType.required}")
    private SourceType sourceType;

    @Schema(description = "Date of the cash flow")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.flowDate.required}")
    private LocalDate flowDate;

    @Schema(description = "Amount of the cash flow", defaultValue = "1000.00")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.amount.required}")
    @Positive(message = "{com.oracle.dto.CashFlowDTO.amount.positive}")
    private BigDecimal amount;

    @Schema(description = "Currency code", defaultValue = "INR")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.currency.required}")
    @Size(min = 3, max = 3, message = "{com.oracle.dto.CashFlowDTO.currency.size}")
    private String currency;

    @Schema(description = "Type of cash flow: INCOME or EXPENSE", defaultValue = "INCOME")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.flowType.required}")
    private FlowType flowType;

    @Schema(description = "Status of the cash flow", defaultValue = "EXPECTED")
    @NotNull(message = "{com.oracle.dto.CashFlowDTO.status.required}")
    private FlowStatus status;
}