package my.lottery.repository;

import my.lottery.rest.dto.EuroMillionsTicketDto;

import java.util.List;

public interface EuroMillionsDataRepository {

     List<EuroMillionsTicketDto> getHistoryResults();
}
