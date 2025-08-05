package com.oracle.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.oracle.entities.Scenarios;

@Repository
public interface ScenariosRepository extends CrudRepository<Scenarios, String> {

    @Modifying
    @Query("UPDATE Scenarios s SET s.impactFactor = :newImpactFactor WHERE s.scenarioId = :scenarioId")
    int updateImpactFactor(String scenarioId, double newImpactFactor);
}