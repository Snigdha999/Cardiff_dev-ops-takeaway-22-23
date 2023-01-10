package imc.mscdevopstakeaway22_23;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HTTPConnectionTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/Hello.html",
                String.class)).contains("Hello2");
    }

    //    this test may get commented out as it will fail when the localhost
    //    database does not have the correct items in it.
    @Test
    public void dbMenuTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/Menu",
                String.class)).contains("Chips");
    }
}
