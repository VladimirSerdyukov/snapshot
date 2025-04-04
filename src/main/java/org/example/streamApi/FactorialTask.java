package org.example.streamApi;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    long factorialN;

    public FactorialTask(long n) {
        this.factorialN = n;
    }

    @Override
    protected Long compute() {
        if(factorialN < 2){
            return 1L;
        } else {
            FactorialTask branch1 = new FactorialTask(factorialN - 1L);
            branch1.fork();
            return branch1.join() * factorialN;
        }
    }
}
