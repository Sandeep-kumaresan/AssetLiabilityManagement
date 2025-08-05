package com.oracle.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyExchangeRateDTO {

    @NotBlank(message = "From currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "From currency must be a valid ISO 3-letter code")
    private String fromCurrency;

    @NotBlank(message = "To currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "To currency must be a valid ISO 3-letter code")
    private String toCurrency;

    @NotNull(message = "Exchange rate must not be null")
    @DecimalMin(value = "0.000001", message = "Rate must be greater than 0")
    private BigDecimal rate;

    @NotNull(message = "Date is required")
    private LocalDate date;

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

    // Getters and setters...
    
    
}