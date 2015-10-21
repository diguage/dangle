package com.diguage.dangle.studies.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 学习示例
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-16 18:19
 */
public class ThreadPoolExecutorDemo {
    @Test
    public void useExecutor() {
        ExecutorService executorService = new java.util.concurrent.ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 2; i < 1000; i++) {
            executorService.execute(new PrimeNumberTask(i));
        }
        executorService.shutdown();
    }
}

class PrimeNumberTask implements Runnable {
    private long number;

    public PrimeNumberTask(long number) {
        this.number = number;
    }

    public void run() {
        boolean isPrimeNumber = true;
        long pow = Math.round(Math.sqrt(number)) + 1;
        for (int i = 2; i < pow; i++) {
            if (number % i == 0) {
                isPrimeNumber = false;
                break;
            }
        }
        if (isPrimeNumber) {
            System.out.println(number);
        }
    }
}