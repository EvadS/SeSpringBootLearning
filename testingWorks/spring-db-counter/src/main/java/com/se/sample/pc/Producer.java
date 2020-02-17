package com.se.sample.pc;


public class Producer implements Runnable {
    private Broker broker;
    private String name;

    public Producer( String name,Broker broker) {
        this.broker = broker;
        this.name = name;
    }

    @Override
    public void run() {
        while (broker.continueProducing)
            System.out.println("Producer produced: ");
        try {
            Thread.sleep(100);
           Integer ii =  broker.get();
        System.out.println("ii " + ii);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

