package com.oracle.service;

import com.oracle.dto.InvestmentsDTO;
import java.util.List;

public interface InvestmentsService {

    List<InvestmentsDTO> retrieveInvestments();
    InvestmentsDTO retrieveInvestment(String investmentId);
    String saveInvestment(InvestmentsDTO investmentDTO);
    String updateInvestmentAmountAndMarketValue(String investmentId, double newAmount, double newMarketValue);
    String deleteInvestment(String investmentId);
}