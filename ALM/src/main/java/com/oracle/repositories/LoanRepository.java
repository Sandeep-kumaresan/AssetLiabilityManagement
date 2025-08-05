package com.oracle.repositories;
import com.oracle.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LoanRepository extends JpaRepository<Loan, String> {
    boolean existsByLoanId(String loanId);}