package org.example.complexTask;

import java.util.concurrent.Callable;

public class ComplexTask implements Runnable {
    private Integer result;
    private String name = "Task :";

    public ComplexTask(int namePrefix){
        this.name = name + namePrefix;
    }

    public void execute(){
        result = (int) (Math.random() * 10);
        System.out.println("Number :" + result);
    }

    @Override
    public void run() {
        execute();
    }


    public Integer getResult() {
        return result;
    }
}
