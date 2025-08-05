package com.oracle.validation;

import com.oracle.dto.InvestmentsDTO;
import com.oracle.service.InvestmentsService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvestmentIdValidator implements ConstraintValidator<ValidateInvestmentId, String> {

    @Autowired
    private InvestmentsService investmentsService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        InvestmentsDTO investmentDTO = investmentsService.retrieveInvestment(value);
        return investmentDTO == null;
    }
}