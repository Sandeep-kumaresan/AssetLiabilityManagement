package com.oracle.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Interest_Rates")
public class InterestRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "rate_date", nullable = false)
    private LocalDate rateDate;

    @Column(name = "effective_rate", nullable = false)
    private double effectiveRate;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getRateDate() {
        return rateDate;
    }
    public void setRateDate(LocalDate rateDate) {
        this.rateDate = rateDate;
    }
    public double getEffectiveRate() {
        return effectiveRate;
    }
    public void setEffectiveRate(double effectiveRate) {
        this.effectiveRate = effectiveRate;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
