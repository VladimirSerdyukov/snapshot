package org.example.virtualBank;

public class ListTransactionOne implements Runnable {

    private String name;
    private BankAccount transmitting;
    private BankAccount receiving;
    private ConcurrentBank bank;

    public ListTransactionOne(String name, BankAccount transmitting,
                              BankAccount receiving, ConcurrentBank bank) {
        this.name = name;
        this.transmitting = transmitting;
        this.receiving = receiving;
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println("Поток " + name + " начал выполняться");
        for (int i = 0; i < 99; i++) {
            bank.transfer(transmitting, receiving, 1l);
            bank.transfer(receiving, transmitting, 1l);
            System.out.println("Операции выполнены " + i + " раз");
        }
        System.out.println("Поток " + name + " завершил свою работу");
    }
}
