package org.example.complexTask;

import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private int countTasks;
    // Использовать CyclicBarrier

    public ComplexTaskExecutor(int countTasks) {
        this.countTasks = countTasks;
    }

    public void executeTasks(int numberOfTasks) {
//        Создает пул потоков и назначает каждому потоку, экземпляр сложной задачи
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < numberOfTasks; i++) {

            Callable call = new ComplexTask(i);
            executor.submit(call);
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        System.out.println(barrier.getParties());
    }

}
