package com.oracle.controller;
import com.oracle.dto.LoanDTO;
import com.oracle.entities.Loan;
import com.oracle.service.LoanService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("api")
@EnableAspectJAutoProxy
@CrossOrigin(origins = "*")
public class LoanControllerImpl implements LoanController {
    @Autowired
    private LoanService loanService;
    @Override
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.retrieveLoans());
    }
    @Override
    public ResponseEntity<Loan> getLoanById(String loanId) {
        return ResponseEntity.ok(loanService.findByLoanId(loanId));
    }
    @Override
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody LoanDTO loanDTO)
{
        Loan loan = loanService.saveLoan(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }
    @Override
    public ResponseEntity<Loan> updateLoan(String loanId, BigDecimal amount, 
BigDecimal interestRate) {
        Loan updatedLoan = loanService.updateLoan(loanId, amount, interestRate);
        return ResponseEntity.ok(updatedLoan);
    }
    @Override
    public ResponseEntity<Void> deleteLoan(String loanId) {
        loanService.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }
}