package org.example;


import org.example.concurrency.BlockingQueue;
import org.example.concurrency.Consumer;
import org.example.concurrency.Producer;
import org.example.iterator.ListInTheMap;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        // Считываем лист формируем карту
        ListInTheMap s = new ListInTheMap();
        Map<String, Integer> d = s.createMap();
        d.entrySet().forEach(System.out::println);

        // Задача по очереди и пулам
        BlockingQueue<Integer> queue = new BlockingQueue<>(10);

        // Создаем пулы потоков
        ExecutorService producerPool = Executors.newFixedThreadPool(3);
        ExecutorService consumerPool = Executors.newFixedThreadPool(2);

        // Запускаем производителей
        for (int i = 0; i < 3; i++) {
            producerPool.execute(new Producer(queue, i));
        }

        // Запускаем потребителей
        for (int i = 0; i < 2; i++) {
            consumerPool.execute(new Consumer(queue, i));
        }

        // Ждем завершения всех задач
        producerPool.shutdown();
        try {
            producerPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Завершаем работу
        consumerPool.shutdownNow();
    }
}

