package com.oracle.controller;

import com.oracle.entities.Debt;
import com.oracle.repositories.DebtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/debts")
public class DebtController {

    @Autowired
    private DebtRepository debtRepository;

    @PostMapping
    public ResponseEntity<Debt> createDebt(@RequestBody Debt debt) {
        Debt saved = debtRepository.save(debt);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Debt>> getAllDebts() {
        return ResponseEntity.ok(debtRepository.findAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Debt>> getByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(debtRepository.findByCustomerId(customerId));
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Debt>> getByAmountGreaterThan(@PathVariable BigDecimal amount) {
        return ResponseEntity.ok(debtRepository.findByAmountGreaterThan(amount));
    }

    @GetMapping("/maturity/{date}")
    public ResponseEntity<List<Debt>> getByMaturityBefore(@PathVariable LocalDate date) {
        return ResponseEntity.ok(debtRepository.findByMaturityDateBefore(date));
    }

    @GetMapping("/collateral/{type}")
    public ResponseEntity<List<Debt>> getByCollateralType(@PathVariable String type) {
        return ResponseEntity.ok(debtRepository.findByCollateralTypeIgnoreCase(type));
    }
}