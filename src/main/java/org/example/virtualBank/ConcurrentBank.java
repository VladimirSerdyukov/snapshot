package org.example.virtualBank;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {

    private final ConcurrentHashMap<UUID, BankAccount> bankAccountHashMap = new ConcurrentHashMap<>();

    BankAccount createAccount(Long deposit) {
        while(true) {
            UUID id = UUID.randomUUID();
            if(!bankAccountHashMap.containsKey(id)) {
                bankAccountHashMap.put(id, new BankAccount(deposit, id));
                return bankAccountHashMap.get(id);
            }
        }
    }

    void transfer(BankAccount transmittingAccount, BankAccount receivingAccount, Long deposit){
        try{
            transmittingAccount.acquireLock();
            receivingAccount.acquireLock();

            if(transmittingAccount.withdraw(deposit)) {
                if(!receivingAccount.deposit(deposit)){
                    transmittingAccount.deposit(deposit);
                }
            }
        } finally {
            transmittingAccount.giveAwayLock();
            receivingAccount.giveAwayLock();
        }
    }

    Long getTotalBalance(){
        return bankAccountHashMap.values().stream().mapToLong(BankAccount::getBalance).sum();
    }
}
