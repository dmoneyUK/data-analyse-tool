package my.lottery.domain.services.drawresults;

import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.domain.data.EuroMillionsTicket;

import java.util.List;
import java.util.Map;

public interface DrawResultManagementService {

    List<EuroMillionsDrawResult> getHistoryResults();

    void updateDrawResults();

}
