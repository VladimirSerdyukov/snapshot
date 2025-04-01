package org.example.virtualBank;

public class BankMain {
        public static void main(String[] args) {
            ConcurrentBank bank = new ConcurrentBank();

            // Создание счетов
            BankAccount account1 = bank.createAccount(1000L);
            BankAccount account2 = bank.createAccount(500L);

            // Перевод между счетами
            Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100L));
            Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200L));

            transferThread1.start();
            transferThread2.start();

            try {
                transferThread1.join();
                transferThread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Вывод общего баланса
            System.out.println("Total balance: " + bank.getTotalBalance());
        }

}
