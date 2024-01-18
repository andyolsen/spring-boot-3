package demo.syncasync;

import java.util.function.Consumer;

public interface Reader {
    void read(String filename, Consumer<Payload> consumer);
}
