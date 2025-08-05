package com.oracle.repositories;

import com.oracle.entities.ScenarioResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScenarioResultsRepository extends JpaRepository<ScenarioResults, UUID> {
}