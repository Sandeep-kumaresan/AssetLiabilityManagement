package com.oracle.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.oracle.entities.Liability;

public interface LiabilityService {
    Liability saveLiability(Liability liability);
    Optional<Liability> getLiabilityById(UUID id);
    List<Liability> getAllLiabilities();
    Liability updateLiability(UUID id, Liability updatedLiability);
    void deleteLiability(UUID id);
}