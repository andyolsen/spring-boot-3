package com.example.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReceiver {

    @JmsListener(destination = "myqueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
