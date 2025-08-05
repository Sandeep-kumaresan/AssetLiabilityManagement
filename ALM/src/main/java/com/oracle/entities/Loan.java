package com.oracle.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Loan")
@Data
public class Loan {

    @Id
    @Column(name = "loan_id", length = 36, nullable = false)
    private String loanId;

    @Column(name = "amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "currency_code", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "interest_rate", precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "asset_type", length = 100)
    private String assetType;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "loan_type", length = 50)
    private String loanType;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "maturity_date", nullable = false)
    private Date maturityDate;

    @Column(name = "status", length = 50)
    private String status;

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
