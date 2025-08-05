package com.oracle.dto;

import com.oracle.validation.ValidCurrencyCode;
import com.oracle.validation.ValidateInvestmentId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import lombok.Data;
import java.sql.Date;

@Data
public class InvestmentsDTO {

    @Schema(description = "Unique identifier for the investment")
    @ValidateInvestmentId
    private String investmentId;

    @Schema(description = "Investment amount", defaultValue = "1000.00")
    @DecimalMin(value = "0.01", message = "{com.oracle.dto.InvestmentsDTO.amount.min}")
    private double amount;

    @Schema(description = "Currency code", defaultValue = "USD")
    @NotBlank(message = "{com.oracle.dto.InvestmentsDTO.currencyCode.notblank}")
    @Size(min = 3, max = 3, message = "{com.oracle.dto.InvestmentsDTO.currencyCode.size}")
    @ValidCurrencyCode
    private String currencyCode;

    @Schema(description = "Interest rate for the investment")
    @DecimalMin(value = "0.0", message = "{com.oracle.dto.InvestmentsDTO.interestRate.min}")
    @DecimalMax(value = "9.9999", message = "{com.oracle.dto.InvestmentsDTO.interestRate.max}")
    private double interestRate;

    @Schema(description = "Type of asset")
    @Size(max = 50, message = "{com.oracle.dto.InvestmentsDTO.assetType.size}")
    private String assetType;

    @Schema(description = "Maturity date of the investment")
    private Date maturityDate;

    @Schema(description = "Acquisition date of the investment")
    @NotNull(message = "{com.oracle.dto.InvestmentsDTO.acquisitionDate.notblank}")
    private Date acquisitionDate;

    @Schema(description = "Status of the investment (Active, Matured, Sold)", defaultValue = "Active")
    @Size(max = 20, message = "{com.oracle.dto.InvestmentsDTO.status.size}")
    private String status;

    @Schema(description = "Description of the investment")
    private String description;

    @Schema(description = "Market value of the investment")
    @DecimalMin(value = "0.0", message = "{com.oracle.dto.InvestmentsDTO.marketValue.min}")
    private double marketValue;
}