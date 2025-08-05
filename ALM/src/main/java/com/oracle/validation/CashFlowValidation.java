package com.oracle.validation;

import com.oracle.dto.CashFlowDTO;
import com.oracle.enums.FlowType;
import com.oracle.enums.SourceType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CashFlowValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CashFlowDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CashFlowDTO dto = (CashFlowDTO) target;

        // Amount validation
        if (dto.getAmount() == null) {
            errors.rejectValue("amount", "amount.required", "Amount is required");
        } else if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("amount", "amount.invalid", "Amount must be greater than 0");
        }

        // Source type validation
        if (dto.getSourceType() == null) {
            errors.rejectValue("sourceType", "sourceType.required", "Source type is required");
        } else if (!(dto.getSourceType() == SourceType.ASSET || dto.getSourceType() == SourceType.LIABILITY)) {
            errors.rejectValue("sourceType", "sourceType.invalid", "Source type must be ASSET or LIABILITY");
        }

     // Flow date validation
        if (dto.getFlowDate() == null) {
            errors.rejectValue("flowDate", "flowDate.required", "Flow date is required");
        } else if (dto.getFlowDate().isAfter(LocalDate.now())) {
            errors.rejectValue("flowDate", "flowDate.future", "Flow date cannot be in the future");
        }

        // Currency validation
        if (dto.getCurrency() == null || dto.getCurrency().length() != 3) {
            errors.rejectValue("currency", "currency.size", "Currency code must be exactly 3 characters");
        }

        // Status validation
        if (dto.getStatus() == null) {
            errors.rejectValue("status", "status.required", "Status is required");
        }

        // Source ID validation
        if (dto.getSourceId() == null) {
            errors.rejectValue("sourceId", "sourceId.required", "Source ID is required");
        }
    }
}