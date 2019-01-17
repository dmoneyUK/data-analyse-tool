package my.lottery.domain.services;

import my.lottery.domain.data.EuroMillionsDrawResult;

import java.util.List;

public interface DrawResultFetchingService {

    List<EuroMillionsDrawResult> fetchEuroMillionDrawResults();
}
