package my.lottery.domain.services.checking.impl;

import lombok.extern.slf4j.Slf4j;
import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.domain.data.EuroMillionsTicket;
import my.lottery.domain.services.checking.CheckingService;
import my.lottery.domain.services.drawresults.DrawResultManagementService;
import my.lottery.domain.services.tickets.TicketManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CheckingServiceImpl implements CheckingService {

    private final DrawResultManagementService drawResultManagementService;
    private final TicketManagementService ticketManagementService;

    @Autowired
    public CheckingServiceImpl(TicketManagementService ticketManagementService, DrawResultManagementService drawResultManagementService) {
        this.ticketManagementService = ticketManagementService;
        this.drawResultManagementService = drawResultManagementService;
    }

    @Override
    public List<EuroMillionsTicket> checkWinnings(String memberId) {

        ticketManagementService.updateTickets();
        drawResultManagementService.updateDrawResults();

        List<EuroMillionsTicket> tickets = ticketManagementService.getAllTickets();
        List<EuroMillionsDrawResult> drawResults = drawResultManagementService.getHistoryResults();

        List<EuroMillionsTicket> winningTickets = tickets.stream().filter(
                ticket -> {
                    boolean result = false;
                    for (EuroMillionsDrawResult drawResult : drawResults) {
                        if (drawResult.getDrawDate().isBefore(ticket.getDrawDate())) {
                            break;
                        }
                        if (drawResult.getDrawDate().isEqual(ticket.getDrawDate())) {
                            result = drawResult.getB1() == ticket.getB1()
                                    || drawResult.getB2() == ticket.getB2()
                                    || drawResult.getB3() == ticket.getB3()
                                    || drawResult.getB4() == ticket.getB4()
                                    || drawResult.getB5() == ticket.getB5()
                                    || drawResult.getS1() == ticket.getS1()
                                    || drawResult.getS2() == ticket.getS2();
                            break;
                        }
                    }
                   return result;
                }
        ).collect(Collectors.toList());

        return winningTickets;
    }
}
