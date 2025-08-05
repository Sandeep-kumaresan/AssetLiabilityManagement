package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oracle.entities.CashFlow;
import com.oracle.enums.FlowType;
import com.oracle.enums.FlowStatus;
import com.oracle.enums.SourceType;
import com.oracle.service.CashFlowService;

@RestController
@RequestMapping("/api/cashflow")
public class CashFlowController {

    @Autowired
    private CashFlowService cashFlowService;

    @PostMapping
    public ResponseEntity<CashFlow> createCashFlow(@RequestBody CashFlow cashFlow) {
        CashFlow saved = cashFlowService.saveCashFlow(cashFlow);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<CashFlow>> getAllCashFlows() {
        return ResponseEntity.ok(cashFlowService.getAllCashFlows());
    }

    @GetMapping("/type/{flowType}")
    public ResponseEntity<List<CashFlow>> getByFlowType(@PathVariable FlowType flowType) {
        return ResponseEntity.ok(cashFlowService.getCashFlowsByType(flowType));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CashFlow>> getByStatus(@PathVariable FlowStatus status) {
        return ResponseEntity.ok(cashFlowService.getCashFlowsByStatus(status));
    }

    @GetMapping("/source/{sourceType}")
    public ResponseEntity<List<CashFlow>> getBySourceType(@PathVariable SourceType sourceType) {
        return ResponseEntity.ok(cashFlowService.getCashFlowsBySourceType(sourceType));
    }
}