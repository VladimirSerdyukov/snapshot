package org.example.virtualBank;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBankTryLock implements ConcurrentBank {

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
        LocalDateTime timeStart = LocalDateTime.now();
        System.out.println(timeStart);
        while (true) {
            if (transmittingAccount.getTryLock() & receivingAccount.getTryLock()) {
                try {
                    transmittingAccount.acquireLock();
                    receivingAccount.acquireLock();

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

            if(timeStart.plusSeconds(3).compareTo(LocalDateTime.now().plusSeconds(10)) == -1){
                System.out.println("Долгое ожидание выполнения транзакции!");
                return;
            }
        }
    }

    public Long getTotalBalance() {
        return bankAccountHashMap.values().stream().mapToLong(BankAccount::getBalance).sum();
    }
}
