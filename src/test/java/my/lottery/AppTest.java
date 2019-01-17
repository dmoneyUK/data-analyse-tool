package my.lottery;

import my.lottery.domain.data.EuroMillionsTicket;
import my.lottery.domain.services.checking.CheckingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @LocalServerPort
    private int port;

    @Autowired CheckingService checkingService;

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

    @Test
    public void checkWinnings() {
        List<EuroMillionsTicket> winningTickets = checkingService.checkWinnings("abc");
        winningTickets.stream().forEach(System.out::println);
    }

}
