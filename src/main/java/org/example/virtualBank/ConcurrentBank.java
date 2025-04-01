package org.example.virtualBank;

public interface ConcurrentBank {

    BankAccount createAccount(Long deposit);
    void transfer(BankAccount transmitting, BankAccount receiving, Long deposit);
    Long getTotalBalance();


}
