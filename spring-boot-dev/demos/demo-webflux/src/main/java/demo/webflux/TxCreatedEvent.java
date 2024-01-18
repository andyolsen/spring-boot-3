package demo.webflux;

import org.springframework.context.ApplicationEvent;

public class TxCreatedEvent extends ApplicationEvent {

    public TxCreatedEvent(Tx source) {
        super(source);
    }
}
