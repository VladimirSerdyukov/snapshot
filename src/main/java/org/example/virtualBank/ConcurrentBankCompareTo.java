package org.example.virtualBank;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBankCompareTo implements ConcurrentBank{

    private final ConcurrentHashMap<UUID, BankAccount> bankAccountHashMap = new ConcurrentHashMap<>();

    public BankAccount createAccount(Long deposit) {
        while (true) {
            UUID id = UUID.randomUUID();
            if (!bankAccountHashMap.containsKey(id)) {
                bankAccountHashMap.put(id, new BankAccount(deposit, id));
                return bankAccountHashMap.get(id);
            }
        }
    }

    public void transfer(BankAccount transmittingAccount, BankAccount receivingAccount, Long deposit) {
        try {
            if ((receivingAccount.getId().compareTo(transmittingAccount.getId())) == -1) {
                transmittingAccount.acquireLock();
                receivingAccount.acquireLock();
            } else {
                receivingAccount.acquireLock();
                transmittingAccount.acquireLock();
            }

            if (transmittingAccount.withdraw(deposit)) {
                if (!receivingAccount.deposit(deposit)) {
                    transmittingAccount.deposit(deposit);
                }
            }
        } finally {
            transmittingAccount.giveAwayLock();
            receivingAccount.giveAwayLock();
        }
    }

    public Long getTotalBalance() {
        return bankAccountHashMap.values().stream().mapToLong(BankAccount::getBalance).sum();
    }
}
