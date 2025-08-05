package com.oracle.dto;
import com.oracle.entities.InterestRate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
@Data
public class AssetDTO {
    @Schema(description = "Unique identifier for the asset", defaultValue = "1")
    private Long id;
    @Schema(description = "Investment amount", defaultValue = "1000.00")
    @DecimalMin(value = "0.01", message = 
"{com.oracle.dto.AssetDTO.investmentAmount.min}")
    private Double investmentAmount;
    @Schema(description = "Currency code (ISO 4217)", defaultValue = "USD")
    @NotBlank(message = "{com.oracle.dto.AssetDTO.currency.notblank}")
    @Size(min = 3, max = 3, message = "{com.oracle.dto.AssetDTO.currency.size}")
    private String currency;
    @Schema(description = "Risk rating of the asset", defaultValue = "Low")
    @Size(max = 20, message = "{com.oracle.dto.AssetDTO.riskRating.size}")
    private String riskRating;
    @Schema(description = "Maturity date of the asset", defaultValue = "2026-08-03")
    private LocalDate maturityDate;
    @Schema(description = "Duration of the asset", defaultValue = "1.0")
    @DecimalMin(value = "0.0", message = "{com.oracle.dto.AssetDTO.duration.min}")
    private Double duration;
    @Schema(description = "Liquidity score (1-5)", defaultValue = "3")
    @DecimalMin(value = "1", message = 
"{com.oracle.dto.AssetDTO.liquidityScore.min}")
    @DecimalMax(value = "10", message = 
"{com.oracle.dto.AssetDTO.liquidityScore.max}")
    private Integer liquidityScore;
    @Schema(description = "Category of the asset", defaultValue = "Fixed Income")
    @Size(max = 50, message = "{com.oracle.dto.AssetDTO.assetCategory.size}")
    private String assetCategory;
    @Schema(description = "Creation date of the asset", defaultValue = "2025-08-03")
    private LocalDate creationDate;
    @Schema(description = "Last updated date of the asset", defaultValue = "2025-08-03")
    private LocalDate lastUpdatedDate;
    @Schema(description = "Associated interest rate")
    private InterestRate interestRate;
}