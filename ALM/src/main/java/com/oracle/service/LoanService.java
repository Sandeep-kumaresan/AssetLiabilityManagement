package com.oracle.service;

import com.oracle.dto.LoanDTO;
import com.oracle.entities.Loan;
import java.math.BigDecimal;
import java.util.List;


public interface LoanService {
    List<Loan> retrieveLoans();
    Loan saveLoan(LoanDTO loanDTO);
    Loan findByLoanId(String loanId);
    Loan updateLoan(String loanId, BigDecimal amount, BigDecimal interestRate);
    void deleteLoan(String loanId);
}
