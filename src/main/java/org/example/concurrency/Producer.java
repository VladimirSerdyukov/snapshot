package org.example.concurrency;

public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int id;

    public Producer(BlockingQueue<Integer> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.enqueue(i);
                System.out.println("Производитель " + id + " добавил элемент " + i);
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
