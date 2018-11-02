package my.lottery.repository;

import my.lottery.model.EuroMillionsResult;

import java.util.List;
import java.util.Map;

public interface EuroMillionsDataRepository {

     List<EuroMillionsResult> getHistoryResults();
}
