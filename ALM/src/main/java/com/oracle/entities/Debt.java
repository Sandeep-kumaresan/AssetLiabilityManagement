package com.oracle.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "debt")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liability_id")
    private Long liabilityId;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;
    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;
    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;
    @Column(name = "maturity_date", nullable = false)
    private LocalDate maturityDate;
    @Column(name = "collateral_type", nullable = false, length = 100)
    private String collateralType;
    @Column(name = "repayment_schedule", nullable = false, length = 100)
    private String repaymentSchedule;
}