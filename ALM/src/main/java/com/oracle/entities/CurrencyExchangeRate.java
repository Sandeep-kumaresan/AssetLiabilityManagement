package com.oracle.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "Currency_Exchange_Rates", uniqueConstraints = {
	    @UniqueConstraint(name = "UNQ_Currency_Exchange_Rates", columnNames = {"from_currency", "to_currency", "date"})
	})
public class CurrencyExchangeRate {
    @Id
    @Column(name = "exchange_id", nullable = false, updatable = false, length = 36)
    private String exchangeId;
    @Column(name = "from_currency", length = 3, nullable = false)
    private String fromCurrency;
    @Column(name = "to_currency", length = 3, nullable = false)
    private String toCurrency;
    @Column(name = "rate", precision = 10, scale = 6, nullable = false)
    private BigDecimal rate;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    public CurrencyExchangeRate() {
    }
    @PrePersist
    public void prePersist() {
        if (exchangeId == null) {
            exchangeId = UUID.randomUUID().toString();
        }
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
