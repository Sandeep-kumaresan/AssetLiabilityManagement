package com.oracle.service;

import java.util.List;
import java.util.UUID;

import com.oracle.entities.Deposit;

public interface DepositService {
    Deposit saveDeposit(Deposit deposit);
    List<Deposit> getAllDeposits();
    Deposit getDepositById(UUID id);
    void deleteDeposit(UUID id);
}