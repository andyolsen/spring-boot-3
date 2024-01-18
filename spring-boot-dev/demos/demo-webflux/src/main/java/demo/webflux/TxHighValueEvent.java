package demo.webflux;

import org.springframework.context.ApplicationEvent;

public class TxHighValueEvent extends ApplicationEvent {

    public TxHighValueEvent(double amount) {
        super(amount);
    }
}
