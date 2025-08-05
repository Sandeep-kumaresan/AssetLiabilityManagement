package com.oracle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oracle.entities.Liability;

import java.util.UUID;

@Repository
public interface LiabilityRepository extends JpaRepository<Liability, UUID> {
    // Custom query examples:
    // List<Liability> findByStatus(String status);
}