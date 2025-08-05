package com.oracle.validation;

import com.oracle.dto.CurrencyExchangeRateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CurrencyExchangeRateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CurrencyExchangeRateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CurrencyExchangeRateDTO dto = (CurrencyExchangeRateDTO) target;

        if (dto.getFromCurrency() == null || !dto.getFromCurrency().matches("^[A-Z]{3}$")) {
            errors.rejectValue("fromCurrency", "fromCurrency.invalid", "From currency must be a valid 3-letter ISO code.");
        }

        if (dto.getToCurrency() == null || !dto.getToCurrency().matches("^[A-Z]{3}$")) {
            errors.rejectValue("toCurrency", "toCurrency.invalid", "To currency must be a valid 3-letter ISO code.");
        }

        if (dto.getRate() == null || dto.getRate().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("rate", "rate.invalid", "Rate must be greater than zero.");
        }

        if (dto.getDate() == null || dto.getDate().isAfter(LocalDate.now().plusYears(10))) {
            errors.rejectValue("date", "date.invalid", "Date is required and must be within a valid range.");
        }
    }
}