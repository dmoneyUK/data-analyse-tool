package my.lottery.services;

import my.lottery.services.data.EuroMillionsTicket;

import java.util.List;

public interface DataFetchingService {

    List<EuroMillionsTicket> fetchEuroMillionTickets();
}
