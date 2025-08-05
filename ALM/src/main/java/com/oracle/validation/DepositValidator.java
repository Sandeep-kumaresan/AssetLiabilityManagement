package com.oracle.validation;

import com.oracle.dto.DepositDTO;
import org.springframework.stereotype.Component;

@Component
public class DepositValidator {

    public void validate(DepositDTO dto) {
        if (dto.getLiabilityId() == null || dto.getLiabilityId() <= 0) {
            throw new IllegalArgumentException("Liability ID must be a positive value.");
        }

        if (dto.getDepositTerm() == null || dto.getDepositTerm() <= 0) {
            throw new IllegalArgumentException("Deposit term must be a positive number.");
        }

        if (dto.getAutoRenew() == null || !(dto.getAutoRenew().equalsIgnoreCase("YES") || dto.getAutoRenew().equalsIgnoreCase("NO"))) {
            throw new IllegalArgumentException("Auto renew must be either 'YES' or 'NO'.");
        }
    }
}