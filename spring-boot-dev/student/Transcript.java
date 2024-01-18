package mypackage;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Transcript {

    private LinkedList<String> messages;
    private int maxSize;
    private boolean verbose;

    public Transcript(int maxSize, boolean verbose) {
        messages = new LinkedList<>();
        this.maxSize = maxSize;
        this.verbose = verbose;
    }

    public void log(String message) {

        if (messages.size() == maxSize) {
            messages.removeFirst();
        }

        if (verbose) {
            message = String.format("[%s] %s", LocalDateTime.now().toString(), message);
        }
        messages.addLast(message);
    }

    public void display() {
        System.out.printf("%d most recent message(s) in transcript:\n", messages.size());
        for (String message: messages) {
            System.out.printf("\t%s\n", message);
        }
    }
}