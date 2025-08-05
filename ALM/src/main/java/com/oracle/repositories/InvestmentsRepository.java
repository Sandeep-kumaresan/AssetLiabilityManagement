package com.oracle.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.oracle.entities.Investments;

@Repository
public interface InvestmentsRepository extends CrudRepository<Investments, String> {

    @Modifying
    @Query("UPDATE Investments i SET i.amount = :newAmount, i.marketValue = :newMarketValue WHERE i.investmentId = :investmentId")
    int updateAmountAndMarketValue(String investmentId, double newAmount, double newMarketValue);
}