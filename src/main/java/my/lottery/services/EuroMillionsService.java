package my.lottery.services;

import my.lottery.model.EuroMillionsResult;

import java.util.List;
import java.util.Map;

public interface EuroMillionsService {

    List<EuroMillionsResult> getHistoryResults();

    Map<Integer, Integer> getHistoryResultsInPosition(String position);

    EuroMillionsResult getLuckyDip();

}
