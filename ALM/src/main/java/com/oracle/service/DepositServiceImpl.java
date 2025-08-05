package com.oracle.service;


import com.oracle.entities.Deposit;
import com.oracle.repositories.DepositRepository;
import com.oracle.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Deposit saveDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    @Override
    public Deposit getDepositById(UUID id) {
        return depositRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteDeposit(UUID id) {
        depositRepository.deleteById(id);
    }
}