package my.lottery.repository;

import my.lottery.rest.dto.EuroMillionsResultDto;

import java.util.List;

public interface EuroMillionsDataRepository {

     List<EuroMillionsResultDto> getHistoryResults();
}
