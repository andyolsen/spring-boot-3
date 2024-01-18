package demo.syncasync;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

@Log4j2
@Component
@Lazy
class SynchronousReader implements Reader {

    @Override
    public void read(String filename, Consumer<Payload> consumer) {
        try (InputStream in = new FileInputStream(filename)) {
            byte[] data = new byte[1024];
            int res;
            while ((res = in.read(data, 0, data.length)) != -1) {
                consumer.accept(Payload.from(data, res));
            }
        }
        catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}