package com.oracle.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
@Data
public class InterestRateDTO {
    @Schema(description = "Unique identifier for the interest rate", defaultValue = "1")
    private Long id;
    @Schema(description = "Date the rate is effective", defaultValue = "2025-08-03")
    private LocalDate rateDate;
    @Schema(description = "Effective interest rate", defaultValue = "0.05")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.InterestRateDTO.effectiveRate.min}")
    private Double effectiveRate;
    @Schema(description = "Currency code (ISO 4217)", defaultValue = "USD")
    @NotBlank(message = "{com.oracle.dto.InterestRateDTO.currency.notblank}")
    @Size(min = 3, max = 3, message = 
"{com.oracle.dto.InterestRateDTO.currency.size}")
    private String currency;
}