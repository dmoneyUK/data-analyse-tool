package my.lottery.repository;

import my.lottery.services.data.EuroMillionsTicket;

import java.util.List;

public interface EuroMillionsDataRepository {

     List<EuroMillionsTicket> getHistoryResults();
}
