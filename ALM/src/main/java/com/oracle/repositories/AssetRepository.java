package com.oracle.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oracle.entities.Asset;
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    // Optional: Query by asset type or interest rate
    // @Query("SELECT a FROM Asset a WHERE a.DTYPE = 'Investment' AND a.interestRate.effectiveRate > :rate")
    // List<Asset> findInvestmentsByInterestRateGreaterThan(double rate);
 @Modifying
    @Query("UPDATE Asset a SET a.investmentAmount = :investmentAmount, a.duration = :duration WHERE a.id = :id")
    int updateAmountAndDuration(Long id, Double investmentAmount, Double duration);
}