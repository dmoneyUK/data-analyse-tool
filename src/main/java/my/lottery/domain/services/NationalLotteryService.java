package my.lottery.domain.services;

import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.domain.data.EuroMillionsTicket;

import java.util.List;
import java.util.Map;

public interface NationalLotteryService {

    List<EuroMillionsDrawResult> getHistoryResults();

    Map<Integer, Integer> getHistoryResultsInPosition(String position);

    EuroMillionsTicket getLuckyDip();

}
