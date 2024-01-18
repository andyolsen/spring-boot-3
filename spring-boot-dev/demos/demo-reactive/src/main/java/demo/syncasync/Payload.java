package demo.syncasync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
class Payload {

    private final byte[] bytes;
    private final int length;

    public static Payload from(byte[] bytes, int len) {
        return new Payload(bytes, len);
    }

    @Override
    public String toString() {
        return String.format("[*****THREAD %d PROCESSING PAYLOAD*****] %s", Thread.currentThread().threadId(),  new String(bytes));
    }
}