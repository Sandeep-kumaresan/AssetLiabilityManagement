package com.oracle.controller;
import com.oracle.dto.LoanDTO;
import com.oracle.entities.Loan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
public interface LoanController {
    @GetMapping("/loans")
    ResponseEntity<List<Loan>> getAllLoans();
    @GetMapping("/loans/{loanId}")
    ResponseEntity<Loan> getLoanById(@PathVariable String loanId);
    @PostMapping("/loans")
    ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanDTO);
    @PatchMapping("/loans/{loanId}/{amount}/{interestRate}")
    ResponseEntity<Loan> updateLoan(
            @PathVariable String loanId,
            @PathVariable BigDecimal amount,
            @PathVariable BigDecimal interestRate
    );
    @DeleteMapping("/loans/{loanId}")
    ResponseEntity<Void> deleteLoan(@PathVariable String loanId);
}