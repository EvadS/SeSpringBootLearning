package com.se.sample.pc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Broker
{
    private int counter=50;
    public Boolean continueProducing = Boolean.TRUE;


    public synchronized Integer get() {
        while (counter<0 || counter > 100) {
                continueProducing = Boolean.FALSE;
        }

        counter--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + counter);
        notify();

        return counter;
    }

    public synchronized void put() {
        while (counter<0 || counter > 100) {
            continueProducing = Boolean.FALSE;
        }
        counter++;

        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + counter);
        notify();
    }
}