package com.diguage.dangle.studies.concurrent;

import org.junit.Test;

/**
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-18 09:21
 */
public class ThreadDemo {
    @Test
    public void testThreadQuit() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Finished!");
            }
        });

        thread.start();
    }
}
