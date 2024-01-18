package demo.webflux.rest;

import demo.webflux.TxService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@Log4j2
@ActiveProfiles("controller-style-endpoint")
@Import({TxRestController.class, TxService.class})
public class TxRestControllerTest extends TxRestBaseTest {

    @BeforeAll
    static void before() {
        log.info("Running tests for controller-style-endpoint");
    }

    TxRestControllerTest(@Autowired WebTestClient client) {
        super(client);
    }
}
