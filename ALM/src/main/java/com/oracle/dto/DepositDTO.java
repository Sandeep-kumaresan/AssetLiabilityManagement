package com.oracle.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DepositDTO {

    @NotNull
    private Long liabilityId;

    @Positive
    private Integer depositTerm;

    @NotNull
    private String autoRenew; // Consider using Boolean for clarity

    // Getters and Setters
    public Long getLiabilityId() {
        return liabilityId;
    }

    public void setLiabilityId(Long liabilityId) {
        this.liabilityId = liabilityId;
    }

    public Integer getDepositTerm() {
        return depositTerm;
    }

    public void setDepositTerm(Integer depositTerm) {
        this.depositTerm = depositTerm;
    }

    public String getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(String autoRenew) {
        this.autoRenew = autoRenew;
    }
}
