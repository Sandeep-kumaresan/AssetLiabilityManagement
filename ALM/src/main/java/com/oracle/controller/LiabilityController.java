package com.oracle.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.entities.Liability;
import com.oracle.service.LiabilityService;

@RestController
@RequestMapping("/api/liabilities")
public class LiabilityController {

    @Autowired
    private LiabilityService liabilityService;

    @PostMapping
    public ResponseEntity<Liability> create(@RequestBody Liability liability) {
        return ResponseEntity.ok(liabilityService.saveLiability(liability));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Liability> get(@PathVariable UUID id) {
        return liabilityService.getLiabilityById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Liability> getAll() {
        return liabilityService.getAllLiabilities();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        liabilityService.deleteLiability(id);
    }
}
