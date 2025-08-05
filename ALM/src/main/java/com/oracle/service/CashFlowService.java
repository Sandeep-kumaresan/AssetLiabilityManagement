package com.oracle.service;

import java.util.List;
import com.oracle.entities.CashFlow;
import com.oracle.enums.FlowType;
import com.oracle.enums.FlowStatus;
import com.oracle.enums.SourceType;

public interface CashFlowService {

    List<CashFlow> getCashFlowsByType(FlowType flowType);

    List<CashFlow> getCashFlowsByStatus(FlowStatus status);

    List<CashFlow> getCashFlowsBySourceType(SourceType sourceType);

    CashFlow saveCashFlow(CashFlow cashFlow);

    List<CashFlow> getAllCashFlows();
}