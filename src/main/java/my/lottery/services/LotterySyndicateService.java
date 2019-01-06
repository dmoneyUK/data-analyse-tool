package my.lottery.services;

import my.lottery.rest.dto.EuroMillionsTicketDto;

import java.util.List;

public interface LotterySyndicateService {

    List<EuroMillionsTicketDto> getTickets();
}
