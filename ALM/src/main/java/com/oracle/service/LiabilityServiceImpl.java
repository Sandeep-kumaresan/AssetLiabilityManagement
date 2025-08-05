package com.oracle.service;


import com.oracle.entities.Liability;
import com.oracle.repositories.LiabilityRepository;
import com.oracle.service.LiabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiabilityServiceImpl implements LiabilityService {

    @Autowired
    private LiabilityRepository liabilityRepository;

    @Override
    public Liability saveLiability(Liability liability) {
        return liabilityRepository.save(liability);
    }

    @Override
    public Optional<Liability> getLiabilityById(UUID id) {
        return liabilityRepository.findById(id);
    }

    @Override
    public List<Liability> getAllLiabilities() {
        return liabilityRepository.findAll();
    }

    @Override
    public Liability updateLiability(UUID id, Liability updatedLiability) {
        return liabilityRepository.findById(id).map(existing -> {
            existing.setLiabilityType(updatedLiability.getLiabilityType());
            existing.setLiabilityValue(updatedLiability.getLiabilityValue());
            // update other fields...
            return liabilityRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Liability not found"));
    }

    @Override
    public void deleteLiability(UUID id) {
        liabilityRepository.deleteById(id);
    }
}