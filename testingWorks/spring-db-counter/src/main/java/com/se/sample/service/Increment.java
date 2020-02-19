package com.se.sample.service;

import com.se.sample.helper.ThreadHelper;
import com.se.sample.helper.ThreadNameHeleper;

import java.util.concurrent.locks.ReentrantLock;

public class Increment implements  Runnable {

    private Counter counter;
    private String name;
    private ReentrantLock locker;
    private ThreadNameHeleper threadNameHeleper;

    /**
     *
     * @param counter
     * @param locker
     * @param threadNameHeleper
     */
    public Increment( Counter counter, ReentrantLock locker,   ThreadNameHeleper threadNameHeleper) {
        this.locker = locker;
        this.counter = counter;
        this.threadNameHeleper = threadNameHeleper;

        threadNameHeleper.increaseIncrement();
        this.name = threadNameHeleper.getIncrementName();
    }

    @Override
    public void run() {
        try {
            while (true) {

                if (ThreadHelper.checkBreakCondition(locker, name, this.counter.continueProducing))
                    break;

                counter.increment(counter.getIncrementValue(), this.name);
                Thread.sleep(1000);
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();

        } finally {
            // situation when we got exception from counter
            // or exception from  ThreadHelper
            if (locker.isLocked()) {
                locker.unlock();
            }

            System.out.println(String.format("%s  finished its job; terminating...",name ));
            threadNameHeleper.decreaseDecrement();
        }
    }
}
