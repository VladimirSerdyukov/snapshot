package org.example.concurrency;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int id;

    public Consumer(BlockingQueue<Integer> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = queue.dequeue();
                System.out.println("Потребитель " + id + " извлек элемент " + item);
                Thread.sleep((int) (Math.random() * 1500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

