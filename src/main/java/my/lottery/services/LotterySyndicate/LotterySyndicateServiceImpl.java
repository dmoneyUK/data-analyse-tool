package my.lottery.services.LotterySyndicate;

import my.lottery.rest.dto.EuroMillionsTicketDto;
import my.lottery.services.DataFetchingService;
import my.lottery.services.LotterySyndicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotterySyndicateServiceImpl implements LotterySyndicateService {

    private DataFetchingService dataFetchingService;

    @Autowired
    public LotterySyndicateServiceImpl(@Qualifier("lotterySyndicateDataFetchingService") DataFetchingService dataFetchingService) {
        this.dataFetchingService = dataFetchingService;
    }

    @Override
    public List<EuroMillionsTicketDto> getTickets() {
         dataFetchingService.fetchEuroMillionTickets();
         return new ArrayList<>();
    }
}
