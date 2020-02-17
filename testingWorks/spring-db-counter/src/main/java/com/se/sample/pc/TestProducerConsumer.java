package com.se.sample.pc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestProducerConsumer
{

    public static void main(String args[])
    {
        try
        {
            Broker broker = new Broker();

            ExecutorService threadPool = Executors.newFixedThreadPool(3);


            threadPool.execute(new Consumer("1", broker));
            threadPool.execute(new Consumer("2", broker));
            threadPool.execute(new Producer("13", broker));




          //   threadPool.shutdown();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}