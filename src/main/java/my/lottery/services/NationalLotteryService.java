package my.lottery.services;

import my.lottery.rest.dto.EuroMillionsTicketDto;
import my.lottery.services.data.EuroMillionsTicket;

import java.util.List;
import java.util.Map;

public interface NationalLotteryService {

    List<EuroMillionsTicket> getHistoryResults();

    Map<Integer, Integer> getHistoryResultsInPosition(String position);

    EuroMillionsTicket getLuckyDip();

}
