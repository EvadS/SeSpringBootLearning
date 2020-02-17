package com.se.sample.pc;


public class Consumer implements Runnable {

    private String name;
    private Broker broker;


    public Consumer(String name, Broker broker) {
        this.name = name;
        this.broker = broker;
    }


    @Override
    public void run()
    {
        try
        {
            while (broker.continueProducing )
            {
                Thread.sleep(1000);
                Integer ii =  broker.get();
                System.out.println("Consumer " + this.name + " processed data from broker: " + ii);

            }

            System.out.println("Comsumer " + this.name + " finished its job; terminating.");
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

}