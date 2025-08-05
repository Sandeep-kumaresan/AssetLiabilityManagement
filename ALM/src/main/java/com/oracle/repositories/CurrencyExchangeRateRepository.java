package com.oracle.repositories;

import com.oracle.entities.CurrencyExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRate, String> {
    // Optional: method to check existence by fromCurrency, toCurrency, and date
}