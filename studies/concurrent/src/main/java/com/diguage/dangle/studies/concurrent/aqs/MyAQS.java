package com.diguage.dangle.studies.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS 使用实例
 * <p/>
 * Coder：D瓜哥，http://www.diguage.com/
 * <p/>
 * Date: 2015-10-19 17:14
 */
class SimpleLock extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = -7316320116933187634L;


    @Override
    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public void tryLock() {
        tryAcquire(1);
    }

    public void unlock() {
        release(1);
    }

    public boolean isLocked() {
        return isHeldExclusively();
    }
}

public class MyAQS {
    public static void main(String[] args) throws InterruptedException {
        final SimpleLock lock = new SimpleLock();
        lock.lock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    lock.lock();
                    System.out.println(
                            Thread.currentThread().getId() + "\tacquired the lock!");
                    lock.unlock();
                }
            }, "Thread-" + (i + 1)).start();
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("main thread unlock!");
        lock.unlock();
    }
}
