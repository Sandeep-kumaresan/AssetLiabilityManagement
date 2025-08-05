package com.oracle.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.oracle.entities.InterestRate;
public interface InterestRateRepository extends JpaRepository<InterestRate, Long> {
@Modifying
    @Query("UPDATE InterestRate ir SET ir.effectiveRate = :effectiveRate WHERE ir.id = :id")
    int updateEffectiveRate(Long id, Double effectiveRate);
}