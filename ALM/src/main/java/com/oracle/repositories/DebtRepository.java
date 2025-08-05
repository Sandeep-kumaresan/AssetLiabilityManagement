package com.oracle.repositories;

import com.oracle.entities.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {

    // Find all debts by customer ID
    @Query("SELECT d FROM Debt d WHERE d.customerId = :customerId")
    List<Debt> findByCustomerId(@Param("customerId") Long customerId);

    // Find debts with amount greater than a threshold
    @Query("SELECT d FROM Debt d WHERE d.amount > :amount")
    List<Debt> findByAmountGreaterThan(@Param("amount") BigDecimal amount);

    // Find debts maturing before a specific date
    @Query("SELECT d FROM Debt d WHERE d.maturityDate < :date")
    List<Debt> findByMaturityDateBefore(@Param("date") LocalDate date);

    // Find debts by collateral type (case-insensitive)
    @Query("SELECT d FROM Debt d WHERE LOWER(d.collateralType) = LOWER(:collateralType)")
    List<Debt> findByCollateralTypeIgnoreCase(@Param("collateralType") String collateralType);
}
