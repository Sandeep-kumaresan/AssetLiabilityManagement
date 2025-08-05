package com.oracle.validation;
import com.oracle.dto.LiabilityDTO;
import org.springframework.stereotype.Component;

@Component
public class LiabilityValidator {

    public void validate(LiabilityDTO dto) {
        if (dto.getValue() <= 0) {
            throw new IllegalArgumentException("Liability value must be positive.");
        }

        if (dto.getLiabilityId() == null || dto.getLiabilityId().isEmpty()) {
            throw new IllegalArgumentException("Liability ID is required.");
        }
    }
}