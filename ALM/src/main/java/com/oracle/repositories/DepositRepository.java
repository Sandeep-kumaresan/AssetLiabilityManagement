package com.oracle.repositories;

import com.oracle.entities.Deposit;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, UUID> {

    // Optional: Custom query methods
	@Query("SELECT d FROM Deposit d WHERE d.liability.liabilityId = :liabilityId")
	Deposit findByLiabilityId(@Param("liabilityId") UUID liabilityId);


    boolean existsByLiability_LiabilityId(UUID liabilityId);

}