package com.oracle.service;
import com.oracle.dto.InterestRateDTO;
import java.util.List;
public interface InterestRateService {
    List<InterestRateDTO> retrieveInterestRates();
    InterestRateDTO retrieveInterestRate(Long id);
    String saveInterestRate(InterestRateDTO interestRateDTO);
    String updateInterestRateEffectiveRate(Long id, Double effectiveRate);
    String deleteInterestRate(Long id);
}











