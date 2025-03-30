package org.example.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private final int capacity;
    private final Queue<T> queue = new LinkedList<>();
    private final Object lock = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacity) {
                lock.wait();
            }
            queue.offer(item);
            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            T item = queue.poll();
            lock.notifyAll();
            return item;
        }
    }

    public int size() {
        synchronized (lock) {
            return queue.size();
        }
    }


}
