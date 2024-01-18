package demo.syncasync;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

@Log4j2
@Component
@Lazy
class AsynchronousReader implements Reader, CompletionHandler<Integer, ByteBuffer> {

    private long position;
    private AsynchronousFileChannel fileChannel;
    private Consumer<Payload> consumer;

    public void read(String filename, Consumer<Payload> c) {

        this.consumer = c;

        try {
            this.fileChannel = AsynchronousFileChannel.open(
                    Paths.get(filename),
                    StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            this.fileChannel.read(buffer, 0, buffer, this);
        }
        catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    public void completed(Integer bytesRead, ByteBuffer buffer) {

        // Have we finished reading the file yet?
        if (bytesRead < 0)
            return;

        // Flip the FileChannel buffer from write-mode to read-mode, then read the bytes from it.
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);

        // Put the data into our Payload object, and consume (process) it.
        consumer.accept(Payload.from(data, data.length));

        // Emulate a slow stream.
        try { Thread.sleep(2000); } catch (InterruptedException ex) {}

        // Clear the FileChannel buffer, and fire off the next read.
        buffer.clear();
        this.position = this.position + bytesRead;
        this.fileChannel.read(buffer, this.position, buffer, this);
    }

    @Override
    public void failed(Throwable ex, ByteBuffer attachment) {
        log.error(ex);
    }
}