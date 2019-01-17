package my.lottery.rest;

import my.lottery.rest.dto.EuroMillionsTicketDto;
import my.lottery.domain.services.tickets.TicketManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LotterySyndicateApi {
    @Autowired
    private TicketManagementService ticketManagementService;

    @RequestMapping(method = RequestMethod.GET, value = "/lottery-syndicate")
    public List<EuroMillionsTicketDto> getHistoryResults() {
        ticketManagementService.updateTickets();
        return ticketManagementService.getAllTickets()
                                      .stream()
                                      .map(ticket -> new EuroMillionsTicketDto(ticket))
                                      .collect(Collectors.toList());
    }

}
