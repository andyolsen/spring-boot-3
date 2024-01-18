package demo.webflux.rest;

import demo.webflux.TxService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@Log4j2
@ActiveProfiles("handler-style-endpoint")
@Import({TxRestHandlerConfiguration.class, TxRestHandler.class, TxService.class})
public class TxRestHandlerTest extends TxRestBaseTest {

    @BeforeAll
    static void before() {
        log.info("Running tests for handler-style-endpoint");
    }

    TxRestHandlerTest(@Autowired WebTestClient client) {
        super(client);
    }
}
