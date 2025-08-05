package com.oracle.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Liabilities")
public class Liability {

    @Id
    @GeneratedValue
    @Column(name = "liability_id", columnDefinition = "CHAR(36)")
    private UUID liabilityId;

    @Column(name = "liability_type", nullable = false, length = 100)
    private String liabilityType;

    @Column(name = "liability_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal liabilityValue;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Column(name = "interest_rate", precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "risk_weight", precision = 5, scale = 4, nullable = false)
    private BigDecimal riskWeight = BigDecimal.ZERO;

    @Column(name = "status", length = 50, nullable = false)
    private String status = "Active";

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    // Getters and Setters

	public UUID getLiabilityId() {
		return liabilityId;
	}

	public void setLiabilityId(UUID liabilityId) {
		this.liabilityId = liabilityId;
	}

	public String getLiabilityType() {
		return liabilityType;
	}

	public void setLiabilityType(String liabilityType) {
		this.liabilityType = liabilityType;
	}

	public BigDecimal getLiabilityValue() {
		return liabilityValue;
	}

	public void setLiabilityValue(BigDecimal liabilityValue) {
		this.liabilityValue = liabilityValue;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getRiskWeight() {
		return riskWeight;
	}

	public void setRiskWeight(BigDecimal riskWeight) {
		this.riskWeight = riskWeight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

   
    
}