package org.example.complexTask;

import java.util.concurrent.Callable;

public class ComplexTask implements Callable<Integer> {

    String name = "Task :";

    public ComplexTask(int namePrefix){
        this.name = name + namePrefix;
    }

    public Integer execute(){
        System.out.println("Выполнение сверхсложной задачи");
        return (int) (Math.random() * 10);
    }

    @Override
    public Integer call() throws Exception {
        return execute();
    }
}
