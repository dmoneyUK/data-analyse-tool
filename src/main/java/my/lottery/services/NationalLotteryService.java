package my.lottery.services;

import my.lottery.rest.dto.EuroMillionsTicketDto;

import java.util.List;
import java.util.Map;

public interface NationalLotteryService {

    List<EuroMillionsTicketDto> getHistoryResults();

    Map<Integer, Integer> getHistoryResultsInPosition(String position);

    EuroMillionsTicketDto getLuckyDip();

}
