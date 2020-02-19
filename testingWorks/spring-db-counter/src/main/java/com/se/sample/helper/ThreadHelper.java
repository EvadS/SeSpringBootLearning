package com.se.sample.helper;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadHelper {


    private static final Integer decrementValue = 3;
    private static final Integer incrementValue = 2;
    private static final int UPPER_LINE = 100;
    private static final int BOTTOM_LINE = 0;


    // one place for check top and bottom line per counter
    public static boolean checkBreakCondition(ReentrantLock locker, String name, boolean continueProducing) {
        locker.lock();

        if (!continueProducing) {
            System.out.println(String.format("Останавливаем в %s", name));
            locker.unlock();

            return true;
        }

        locker.unlock();

        return false;
    }
}