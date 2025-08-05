package com.oracle.entities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyConverter {

    private final Map<String, Double> exchangeRatesToUSD = new HashMap<>();

    public CurrencyConverter() {
        // Fixed exchange rates to USD (as of hypothetical date, e.g., August 2025)
        exchangeRatesToUSD.put("USD", 1.0);
        exchangeRatesToUSD.put("EUR", 1.1); // 1 EUR = 1.1 USD
        // Add more currencies as needed
    }

    public double convertToUSD(double amount, String currencyCode) {
        if (!exchangeRatesToUSD.containsKey(currencyCode)) {
            throw new IllegalArgumentException("Unsupported currency code: " + currencyCode);
        }
        return amount * exchangeRatesToUSD.get(currencyCode);
    }
}