package demo.syncasync;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        // Get SynchronousReader or AsynchronousReader bean. Uncomment one of these statements.
        Reader reader = context.getBean(SynchronousReader.class);
        // Reader reader = context.getBean(AsynchronousReader.class);

        // Use the bean to read a large-ish file.
        doRead("data/macbeth.xml", reader);
    }

    private static void doRead(String filename, Reader reader) {

        reader.read(filename, bb -> System.out.println(bb));

        for (int i = 0; i < 10; i++) {
            System.out.println("[*****MAIN THREAD DOING USEFUL WORK*****]");
            try { Thread.sleep(1000); }  catch (InterruptedException ex) {}
        }
    }
}
