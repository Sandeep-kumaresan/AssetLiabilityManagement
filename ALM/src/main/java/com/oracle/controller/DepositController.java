package com.oracle.controller;

import com.oracle.entities.Deposit;
import com.oracle.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping
    public ResponseEntity<Deposit> create(@RequestBody Deposit deposit) {
        return ResponseEntity.ok(depositService.saveDeposit(deposit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> get(@PathVariable UUID id) {
        Deposit deposit = depositService.getDepositById(id);
        return (deposit != null) ? ResponseEntity.ok(deposit) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Deposit> getAll() {
        return depositService.getAllDeposits();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        depositService.deleteDeposit(id);
    }
}