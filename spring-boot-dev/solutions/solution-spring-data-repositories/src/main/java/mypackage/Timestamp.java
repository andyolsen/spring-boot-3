package mypackage;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class Timestamp {

    private LocalDateTime creationDateTime = LocalDateTime.now();

    public LocalDate date() {
        return creationDateTime.toLocalDate();
    }

    public LocalTime time() {
        return creationDateTime.toLocalTime();
    }
}