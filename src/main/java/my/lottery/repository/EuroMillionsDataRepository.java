package my.lottery.repository;

import my.lottery.domain.data.EuroMillionsTicket;

import java.util.List;

public interface EuroMillionsDataRepository {

     List<EuroMillionsTicket> getHistoryResults();
}
