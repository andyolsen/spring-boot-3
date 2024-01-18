package mypackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ShoppingSession {

    @Value("#{ timestamp.date }")
    private LocalDate startDate;

    @Value("#{ timestamp.time }")
    private LocalTime startTime;

    private String displayTimestampMode;

    @Autowired
    public ShoppingSession(ApplicationArguments args) {
        if (args.containsOption("displayTimestampMode"))
            displayTimestampMode = args.getOptionValues("displayTimestampMode").get(0);
        else
            displayTimestampMode = "none";
    }

    public void displayStartDateTime() {

        if (displayTimestampMode.equals("date") || displayTimestampMode.equals("both"))
            System.out.printf("Shopping session start date: %s\n", startDate);

        if (displayTimestampMode.equals("time") || displayTimestampMode.equals("both"))
            System.out.printf("Shopping session start time: %s\n", startTime);
    }
}