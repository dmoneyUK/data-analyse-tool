package my.lottery.repository;

import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.domain.data.EuroMillionsTicket;

import java.util.List;

public interface DrawResultRepository {

     List<EuroMillionsDrawResult> getAllDrawResults();

     void store(List<EuroMillionsDrawResult> drawResults);

}
