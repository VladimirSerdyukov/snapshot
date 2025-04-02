package org.example;

import org.example.complexTask.ComplexTaskExecutor;
/*
* Слегка изменил задание для очевидности:
* Задачи будут передаваться из main и создаваться в специальном классе CreateTask.
* Суть тестовых задач будет, сводится к подсчету суммы в коллекциях
* */
public class Main {
    public static void main(String[] args) {

        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5); // Количество задач для выполнения

        Runnable testRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " started the test.");

            // Выполнение задач
            taskExecutor.executeTasks(5);

            System.out.println(Thread.currentThread().getName() + " completed the test.");
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


