package my.lottery.repository;

import my.lottery.rest.dto.EuroMillionsResult;

import java.util.List;

public interface EuroMillionsDataRepository {

     List<EuroMillionsResult> getHistoryResults();
}
