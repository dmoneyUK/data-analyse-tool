package my.lottery.services;

import my.lottery.rest.dto.EuroMillionsResultDto;

import java.util.List;
import java.util.Map;

public interface NationalLotteryService {

    List<EuroMillionsResultDto> getHistoryResults();

    Map<Integer, Integer> getHistoryResultsInPosition(String position);

    EuroMillionsResultDto getLuckyDip();

}
