package com.oracle.service;

import com.oracle.dto.LoanDTO;
import com.oracle.entities.Loan;
import com.oracle.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Loan> retrieveLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan saveLoan(LoanDTO loanDTO) {
        if (loanRepository.existsByLoanId(loanDTO.getLoanId())) {
            throw new IllegalArgumentException("Loan ID already exists");
        }
        if (!List.of("Active", "Closed", "Defaulted").contains(loanDTO.getStatus())) {
            throw new IllegalArgumentException("Invalid status: must be Active, Closed, or Defaulted");
        }
        Loan loan = convertToEntity(loanDTO);
        return loanRepository.save(loan);
    }

    @Override
    public Loan findByLoanId(String loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found: " + loanId));
    }

    @Override
    public Loan updateLoan(String loanId, BigDecimal amount, BigDecimal interestRate) {
        Loan loan = findByLoanId(loanId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(String loanId) {
        Loan loan = findByLoanId(loanId);
        loanRepository.delete(loan);
    }

    private Loan convertToEntity(LoanDTO dto) {
        Loan loan = new Loan();
        loan.setLoanId(dto.getLoanId());
        loan.setAmount(dto.getAmount());
        loan.setCurrencyCode(dto.getCurrencyCode());
        loan.setInterestRate(dto.getInterestRate());
        loan.setAssetType(dto.getAssetType());
        loan.setDuration(dto.getDuration());
        loan.setLoanType(dto.getLoanType());
        loan.setStartDate(dto.getStartDate());
        loan.setMaturityDate(dto.getMaturityDate());
        loan.setStatus(dto.getStatus());
        return loan;
    }
}
