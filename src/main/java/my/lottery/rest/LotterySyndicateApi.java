package my.lottery.rest;

import my.lottery.rest.dto.EuroMillionsTicketDto;
import my.lottery.services.LotterySyndicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotterySyndicateApi {
    @Autowired
    private LotterySyndicateService lotterySyndicateService;

    @RequestMapping(method = RequestMethod.GET, value = "/lottery-syndicate")
    public List<EuroMillionsTicketDto> getHistoryResults() {
        return lotterySyndicateService.getTickets();
    }

}
