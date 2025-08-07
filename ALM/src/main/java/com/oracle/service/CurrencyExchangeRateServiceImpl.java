package com.oracle.service;
import com.oracle.entities.CurrencyExchangeRate;
import com.oracle.repositories.CurrencyExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {
    @Autowired
    private CurrencyExchangeRateRepository repository;
    @Override
    public CurrencyExchangeRate save(CurrencyExchangeRate rate) {
        return repository.save(rate);
    }
    @Override
    public Optional<CurrencyExchangeRate> getById(String id) {
        return repository.findById(id);
    }
    @Override
    public List<CurrencyExchangeRate> getAll() {
        return repository.findAll();
    }
    @Override
    public CurrencyExchangeRate update(String id, CurrencyExchangeRate updatedRate) {
        return repository.findById(id).map(existing -> {
            existing.setFromCurrency(updatedRate.getFromCurrency());
            existing.setToCurrency(updatedRate.getToCurrency());
            existing.setRate(updatedRate.getRate());
            existing.setDate(updatedRate.getDate());
            existing.setUpdatedAt(updatedRate.getUpdatedAt());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Exchange Rate not found"));
    }
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}