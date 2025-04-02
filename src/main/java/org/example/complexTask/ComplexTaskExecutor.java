package org.example.complexTask;

import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final CyclicBarrier barrier;
    private final List<ComplexTask> tasks = new CopyOnWriteArrayList<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    private int countTasks;
    // Использовать CyclicBarrier

    public ComplexTaskExecutor(int countTasks) {
        this.countTasks = countTasks;
        this.barrier = new CyclicBarrier(countTasks,
                () -> {
                    Integer result = 0;
                    for (ComplexTask resultOfTask : tasks) {
                        result += resultOfTask.getResult();
                    }
                    System.out.println("Результат : " + result);
                });
    }

    public void executeTasks(int numberOfTasks) {
//        Создает пул потоков и назначает каждому потоку, экземпляр сложной задачи
        try {
            for (int i = 0; i < countTasks; i++) {
                ComplexTask task = new ComplexTask(numberOfTasks);
                tasks.add(task);
                executor.submit(() -> {
                    task.run();
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                });

            }
            executor.shutdown();

            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}