package com.oracle.service;

import com.oracle.entities.CurrencyExchangeRate;
import java.util.*;

public interface CurrencyExchangeRateService {
    CurrencyExchangeRate save(CurrencyExchangeRate rate);
    Optional<CurrencyExchangeRate> getById(String id);
    List<CurrencyExchangeRate> getAll();
    CurrencyExchangeRate update(String id, CurrencyExchangeRate updatedRate);
    void delete(String id);
}