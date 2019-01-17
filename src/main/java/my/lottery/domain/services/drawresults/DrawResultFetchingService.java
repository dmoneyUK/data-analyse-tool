package my.lottery.domain.services.drawresults;

import my.lottery.domain.data.EuroMillionsDrawResult;

import java.util.List;

public interface DrawResultFetchingService {

    List<EuroMillionsDrawResult> fetchEuroMillionDrawResults();
}
