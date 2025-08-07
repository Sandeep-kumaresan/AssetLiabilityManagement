package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.entities.CashFlow;
import com.oracle.enums.FlowType;
import com.oracle.enums.FlowStatus;
import com.oracle.enums.SourceType;
import com.oracle.repositories.CashFlowRepository;

@Service
public class CashFlowServiceImpl implements CashFlowService {
    @Autowired
    private CashFlowRepository cashFlowRepository;
    @Override
    public List<CashFlow> getCashFlowsByType(FlowType flowType) {
        return cashFlowRepository.findByFlowType(flowType);
    }
    @Override
    public List<CashFlow> getCashFlowsByStatus(FlowStatus status) {
        return cashFlowRepository.findByStatus(status);
    }
    @Override
    public List<CashFlow> getCashFlowsBySourceType(SourceType sourceType) {
        return cashFlowRepository.findBySourceType(sourceType);
    }
    @Override
    public CashFlow saveCashFlow(CashFlow cashFlow) {
        return cashFlowRepository.save(cashFlow);
    }
    @Override
    public List<CashFlow> getAllCashFlows() {
        return cashFlowRepository.findAll();
    }
}