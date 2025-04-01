package org.example.virtualBank;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private Long balance = 0L;
    private final ReentrantLock lock = new ReentrantLock();
    private final UUID id;



    public BankAccount(Long deposit, UUID uuid){
        this.balance = deposit;
        id = uuid;
    }

    //Вариант с синхронизацией самого метода: Блокироваться будет
    // весь объект и мы не сможем получить параллельный доступ к методу getBalance
    boolean deposit(Long deposit){
        // пополнение счета
        try {
            lock.lock();
            if(deposit > 0) {
                balance += deposit;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }
    boolean withdraw(Long withdraw){
        // снятие наличных
        try {
            lock.lock();
            if(withdraw > 0) {
                balance -= withdraw;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }
    Long getBalance(){
        // Состояние счета
        return this.balance;
    }

    UUID getId(){
        return id;
    }

    public void acquireLock(){
        lock.lock();
    }

    public void giveAwayLock() {
        lock.unlock();
    }

    public boolean getTryLock(){
        return lock.tryLock();
    }

}
