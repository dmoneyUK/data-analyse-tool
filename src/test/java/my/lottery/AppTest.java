package my.lottery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fetchLotterySyndicateTickets() {
        this.restTemplate.getForObject("http://localhost:" + port + "/lottery-syndicate",
                                       String.class);
    }

    @Test
    public void fetchEuroMillionsDrawResults() {
        this.restTemplate.getForObject("http://localhost:" + port + "/euro-millions/historyResults",
                                       String.class);
    }

}
