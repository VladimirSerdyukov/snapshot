package org.example.virtualBank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankMain {
    public static void main(String[] args) {
        ConcurrentBank bankCompare = new ConcurrentBankCompareTo();
        ConcurrentBank bankTry = new ConcurrentBankTryLock();

        // Создание счетов
        BankAccount account1 = bankCompare.createAccount(1000L);
        BankAccount account2 = bankCompare.createAccount(500L);

        BankAccount account3 = bankTry.createAccount(1000L);
        BankAccount account4 = bankTry.createAccount(1000L);

        ExecutorService executorServiceCompare = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorServiceCompare.execute(new ListTransactionOne(i + "_name", account1, account2, bankCompare));
        }
        executorServiceCompare.shutdown();

        ExecutorService executorServiceTry = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorServiceTry.execute(new ListTransactionOne(i + "_name", account3, account4, bankTry));
        }
        executorServiceTry.shutdown();


//        // Перевод между счетами
//        Thread transferThread2 = new Thread(() -> bankCompare.transfer(account2, account1, 1L));
//        Thread transferThread1 = new Thread(() -> bankCompare.transfer(account1, account2, 2L));
//
//        transferThread1.start();
//        transferThread2.start();
//
//        try {
//            transferThread1.join();
//            transferThread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            }

        // Вывод общего баланса
        System.out.println("Total balance: " + bankCompare.getTotalBalance());
    }

}
