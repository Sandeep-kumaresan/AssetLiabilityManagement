package com.oracle.controller;

import com.oracle.dto.CurrencyExchangeRateDTO;
import com.oracle.entities.CurrencyExchangeRate;
import com.oracle.service.CurrencyExchangeRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/exchange-rates")
public class CurrencyExchangeRateController {

    @Autowired
    private CurrencyExchangeRateService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CurrencyExchangeRateDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            errors.getFieldErrors().forEach(e -> errorMap.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errorMap);
        }

        CurrencyExchangeRate entity = new CurrencyExchangeRate();
        entity.setFromCurrency(dto.getFromCurrency());
        entity.setToCurrency(dto.getToCurrency());
        entity.setRate(dto.getRate());
        entity.setDate(dto.getDate());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(service.save(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyExchangeRate> getById(@PathVariable String id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CurrencyExchangeRate> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}