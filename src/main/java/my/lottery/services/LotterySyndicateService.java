package my.lottery.services;

import my.lottery.services.data.EuroMillionsTicket;

import java.util.List;

public interface LotterySyndicateService {

    List<EuroMillionsTicket> getTickets();
}
