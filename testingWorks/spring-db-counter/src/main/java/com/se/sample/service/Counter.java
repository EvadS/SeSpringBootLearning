package com.se.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final Logger logger = LoggerFactory.getLogger(Counter.class);


    private final Integer decrementValue = 3;
    private final Integer incrementValue = 2;
    private final int UPPER_LINE = 100;
    private final int BOTTOM_LINE = 0;

    private volatile Boolean continueProducing = Boolean.TRUE;
    private AtomicInteger counter = new AtomicInteger(50);

    public synchronized int decrement(int value, String name) {
        if (checkCounterValue(name))
            return counter.get();

        logger.info("Decrement. Thread : {} ,Счетчик  : {}, уменьшаем на: {} ", name, counter.get(), decrementValue);

        counter.updateAndGet(i -> i - value);
        logger.info("==  {} Счетчик уменьшен на  : %s. Значение счетчика    : %s", name, value, counter);

        if (checkCounterValue(name))
            return counter.get();

        return counter.get();
    }

    public synchronized int increment(Integer value, String name) {

        if (checkCounterValue(name))
            return counter.get();

        logger.info("Increment. Thread : {} ,  Счетчик  : {}, увеличиваем на : {}", name, counter.get(), incrementValue);
        counter.updateAndGet(i -> i + value);

        logger.info(name + "== Счетчик увеличен на : {}. Значение счетчика   : {} ", value, counter);

        if (checkCounterValue(name))
            return counter.get();

        return counter.get();
    }

    private synchronized boolean checkCounterValue(String name) {
        if (!continueProducing) {
            return false;
        }

        if (counter.get() < BOTTOM_LINE || counter.get() > UPPER_LINE) {
            logger.info("Вышли за пределы в : %s, счетчик: %s", name, counter);

            continueProducing = Boolean.FALSE;
            return true;
        }

        return false;
    }


    public int getDecrementValue() {
        return decrementValue;
    }

    public int getIncrementValue() {
        return incrementValue;
    }


    public int getCounterValue(){
        return counter.intValue();
    }


    public void setCounter(int counter) {
        this.counter.set(counter);
    }

    public void enableContinueProducing(){
        continueProducing = Boolean.TRUE;
    }

    public Boolean getContinueProducing() {
        return continueProducing;
    }
}