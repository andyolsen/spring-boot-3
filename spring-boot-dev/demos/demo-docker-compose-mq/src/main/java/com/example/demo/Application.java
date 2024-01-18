package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        EmployeeSender sender = context.getBean(EmployeeSender.class);

        sender.sendMessage("Huey");
        sender.sendMessage("Louis");
        sender.sendMessage("Dewey");	    }

}
