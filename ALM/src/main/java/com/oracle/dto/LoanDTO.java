package com.oracle.dto;
import com.oracle.validation.ValidCurrencyCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
@Data
public class LoanDTO {
    @Schema(description = "Unique identifier for the loan", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "{com.oracle.dto.LoanDTO.loanId.notnull}")
    private String loanId;
    @Schema(description = "Loan amount", example = "50000.00")
    @NotNull(message = "{com.oracle.dto.LoanDTO.amount.notnull}")
    @DecimalMin(value = "0.01", message = "{com.oracle.dto.LoanDTO.amount.min}")
    private BigDecimal amount;
    @Schema(description = "Currency code (ISO 4217)", example = "USD")
    @NotBlank(message = "{com.oracle.dto.LoanDTO.currencyCode.notblank}")
    @Size(min = 3, max = 3, message = "{com.oracle.dto.LoanDTO.currencyCode.size}")
    @ValidCurrencyCode
    private String currencyCode;
    @Schema(description = "Interest rate for the loan", example = "0.0500")
    @DecimalMin(value = "0.0", message = 
"{com.oracle.dto.LoanDTO.interestRate.min}")
    @DecimalMax(value = "9.9999", message = 
"{com.oracle.dto.LoanDTO.interestRate.max}")
    private BigDecimal interestRate;
    @Schema(description = "Type of asset backing the loan, if any", example = "Real Estate")
    @Size(max = 50, message = "{com.oracle.dto.LoanDTO.assetType.size}")
    private String assetType;
    @Schema(description = "Loan duration in months", example = "60")
    @Min(value = 1, message = "{com.oracle.dto.LoanDTO.duration.min}")
    private Integer duration;
    @Schema(description = "Type of loan", example = "Retail")
    @Size(max = 50, message = "{com.oracle.dto.LoanDTO.loanType.size}")
    private String loanType;
    @Schema(description = "Start date of the loan", example = "2025-08-03")
    @NotNull(message = "{com.oracle.dto.LoanDTO.startDate.notnull}")
    private Date startDate;
    @Schema(description = "Maturity date of the loan", example = "2030-08-03")
    private Date maturityDate;
    @Schema(description = "Status of the loan (Active, Closed, Defaulted)", example = 
"Active")
    @NotBlank(message = "{com.oracle.dto.LoanDTO.status.notblank}")
    @Size(max = 20, message = "{com.oracle.dto.LoanDTO.status.size}")
    @Pattern(regexp = "Active|Closed|Defaulted", message = 
"{com.oracle.dto.LoanDTO.status.pattern}")
    private String status;
}