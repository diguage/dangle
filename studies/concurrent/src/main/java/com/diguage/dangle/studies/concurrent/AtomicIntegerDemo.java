package com.diguage.dangle.studies.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 自增示例
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-25 09:56
 */
public class AtomicIntegerDemo {
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        int i = race.incrementAndGet();
        System.out.println(i); // 把增加的值输出出来.
    }

    private static final int THREADS_COUNTS = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNTS];

        for (int i = 0; i < THREADS_COUNTS; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

//        while (Thread.activeCount()>1){
//            Thread.yield();
//        }
//        TimeUnit.SECONDS.sleep(2);

        // 为什么打印时对时错? 因为打印的时候,上面增加变量的线程不一定执行完了?
        System.out.println("------ " + race); // 把结果输出出来
    }
}
