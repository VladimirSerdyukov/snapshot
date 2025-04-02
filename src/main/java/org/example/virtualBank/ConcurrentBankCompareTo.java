package org.example.virtualBank;

import java.time.LocalDateTime;
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
        LocalDateTime timeStart = LocalDateTime.now();
        while (true) {
        try {
            if ((receivingAccount.getId().compareTo(transmittingAccount.getId())) == -1) {
                transmittingAccount.acquireLock();
                receivingAccount.acquireLock();
                System.out.println("Захвачены оба лока прямая последовательность:" + Thread.currentThread().getName());
            } else {
                receivingAccount.acquireLock();
                transmittingAccount.acquireLock();
                System.out.println("Захвачены оба лока обратная последовательность:" + Thread.currentThread().getName());
            }

            if (transmittingAccount.withdraw(deposit)) {
                if (!receivingAccount.deposit(deposit)) {
                    transmittingAccount.deposit(deposit);
                    System.out.println("Операция выполнена"  + Thread.currentThread().getName());
                }
            }
        } finally {
            transmittingAccount.giveAwayLock();
            receivingAccount.giveAwayLock();
            System.out.println("Блокировки с кошельков сняты "  + Thread.currentThread().getName());
        }
            if(timeStart.plusSeconds(3).compareTo(LocalDateTime.now()) == -1){
                System.out.println("Долгое ожидание выполнения транзакции! " + Thread.currentThread().getName());
                return;
            }
        }
    }

    public Long getTotalBalance() {
        return bankAccountHashMap.values().stream().mapToLong(BankAccount::getBalance).sum();
    }
}
