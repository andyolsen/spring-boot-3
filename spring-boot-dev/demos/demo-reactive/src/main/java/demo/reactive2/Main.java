package demo.reactive2;

import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Create a publisher - we've used the SubmissionPublisher implementation class.
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();  

        // Create and register a TransformProcessor.
        MyTransformProcessor<String, Integer> transformProcessor = new MyTransformProcessor<>(s -> s.length());  
        publisher.subscribe(transformProcessor);  

        // Register a subscriber. 
        MySubscriber<Integer> subscriber = new MySubscriber<>();  
        transformProcessor.subscribe(subscriber);  

        // Publish some items.  
        System.out.println("Publishing Items...");  
        String[] items = {"matthew", "mark", "luke", "john"};  
        Arrays.asList(items)
                .stream().
                forEach(item -> publisher.submit(item));

        // Tell subscriber's we're done.
        publisher.close();
 
        // Consultancy loop :-)
        try {
            Thread.sleep(2000); 
        }
        catch (InterruptedException ex) {}

        System.out.printf("Subscriber consumed %d items\n", subscriber.consumedItems.size());
    }
}