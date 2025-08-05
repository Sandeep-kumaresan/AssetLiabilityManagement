package com.oracle.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "investments")
@Data
public class Investments {

    @Id
    @Column(name = "investment_id", nullable = false, length = 36)
    private String investmentId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "interest_rate", nullable = false)
    private double interestRate;

    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "maturity_date")
    private Date maturityDate;

    @Column(name = "acquisition_date")
    private Date acquisitionDate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "market_value")
    private double marketValue;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
