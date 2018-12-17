package my.lottery.services;

import my.lottery.rest.dto.EuroMillionsResult;

import java.util.List;
import java.util.Map;

public interface NationalLotteryService {

    List<EuroMillionsResult> getHistoryResults();

    Map<Integer, Integer> getHistoryResultsInPosition(String position);

    EuroMillionsResult getLuckyDip();

}
