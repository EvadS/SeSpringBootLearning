package com.se.sample;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Evgeniy Skiba on 11.05.21
 */
@Component
@EnableScheduling
public class IncreaseCounterJob {

    private static final List<String> availablePaymentMethods = Arrays.asList("INVOICE", "CREDITCARD", "PAYPAL");
    private static final List<String> availableShippingMethods = Arrays.asList("STANDARD", "EXPRESS");

    private static final List<String> availableCountryMethods = Arrays.asList("RU", "US","UA", "BG");

    private final MeterRegistry meterRegistry;

    public IncreaseCounterJob(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    //@Scheduled(fixedDelay = 15000)
    public void simulateNewOrderDE() {
        meterRegistry.counter("orders.created",
                "country", "DE",
                "payment_method", randomPaymentMethod(),
                "shipping_method", randomShippingMethod())
                .increment();
    }

   // @Scheduled(fixedDelay = 15000)
    public void simulateNewOrderAT() {
        meterRegistry.counter("orders.created",
                "country", "AT",
                "payment_method", randomPaymentMethod(),
                "shipping_method", randomShippingMethod())
                .increment();
    }

    @Scheduled(fixedDelay = 2000)
    public void country_metric() {
        meterRegistry.counter("orders.country",
                "country", randomCountry() )
                .increment();
    }


    private String randomCountry() {
        int randomIndex = new Random().nextInt(availablePaymentMethods.size());
        return availableCountryMethods.get(randomIndex);
    }
    private String randomPaymentMethod() {
        int randomIndex = new Random().nextInt(availablePaymentMethods.size());
        return availablePaymentMethods.get(randomIndex);
    }

    private String randomShippingMethod() {
        int randomIndex = new Random().nextInt(availableShippingMethods.size());
        return availableShippingMethods.get(randomIndex);
    }

}