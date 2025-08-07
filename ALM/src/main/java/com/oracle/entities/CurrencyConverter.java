package com.oracle.entities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyConverter {

    private final Map<String, Double> exchangeRatesToUSD = new HashMap<>();

    public CurrencyConverter() {
        exchangeRatesToUSD.put("USD", 1.0);
        exchangeRatesToUSD.put("EUR", 1.1); 
    }

    public double convertToUSD(double amount, String currencyCode) {
        if (!exchangeRatesToUSD.containsKey(currencyCode)) {
            throw new IllegalArgumentException("Unsupported currency code: " + currencyCode);
        }
        return amount * exchangeRatesToUSD.get(currencyCode);
    }
}