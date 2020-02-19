package com.se.sample.service;

import com.se.sample.helper.ThreadHelper;
import com.se.sample.helper.ThreadNameHeleper;

import java.util.concurrent.locks.ReentrantLock;

public class Decrement implements Runnable {

    private ReentrantLock locker;
    private Counter counter;
    private ThreadNameHeleper threadNameHeleper;
    private String name;


    /**
     *
     * @param counter
     * @param locker
     * @param threadNameHeleper
     */
    public Decrement(Counter counter, ReentrantLock locker, ThreadNameHeleper threadNameHeleper) {
        this.locker = locker;
        this.counter = counter;
        this.threadNameHeleper = threadNameHeleper;

        threadNameHeleper.increaseDecrement();
        this.name = threadNameHeleper.getDecrementName();

    }

    @Override
    public void run() {
        try {
            while (this.counter.continueProducing) {
                if (ThreadHelper.checkBreakCondition(locker, name, this.counter.continueProducing))
                    break;

                counter.decrement(counter.getDecrementValue(), this.name);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();

        } finally {
            if (locker.isLocked()) {
                locker.unlock();
            }

            System.out.println(String.format("%s  finished its job; terminating...",name ));
            threadNameHeleper.decreaseDecrement();
        }

    }

    private boolean checkBreakCondition() {

        locker.lock();

        if (!this.counter.continueProducing) {
            System.out.println(String.format("Останавливаем в %s", name));
            locker.unlock();

            return true;
        }

        locker.unlock();
        return false;
    }
}
