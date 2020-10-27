package com.se.server;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * eureka event listener
 * For example: for monitoring eureka service down notification
 *
 * @author hrabbit
 * @date 2018-09-13
 */
@Component
public class EurekaStateChangeListener {
    /**
     * EurekaInstanceCanceledEvent service offline event
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        System.out.println(event.getServerId() + "\t" + event.getAppName() + "Service offline");
    }

    /**
     * EurekaInstanceRegisteredEvent service registration event
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println(instanceInfo.getAppName() + "register");
    }

    /**
     * EurekaInstanceRenewedEvent service renewal event
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.out.println(event.getServerId() + "\t" + event.getAppName() + "Service Renewal");
    }

    /**
     * EurekaRegistryAvailableEvent Eureka Registration Center Launch Event
     * @param event
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.out.println("Registration Startup");
    }

    /**
     * EurekaServerStartedEvent Eureka Server startup event
     * @param event
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.out.println("Eureka Server Startup");
    }
}