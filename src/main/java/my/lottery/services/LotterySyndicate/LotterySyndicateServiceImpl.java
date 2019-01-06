package my.lottery.services.LotterySyndicate;

import my.lottery.services.DataFetchingService;
import my.lottery.services.LotterySyndicateService;
import my.lottery.services.data.EuroMillionsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotterySyndicateServiceImpl implements LotterySyndicateService {

    private DataFetchingService dataFetchingService;

    @Autowired
    public LotterySyndicateServiceImpl(@Qualifier("lotterySyndicateDataFetchingService") DataFetchingService dataFetchingService) {
        this.dataFetchingService = dataFetchingService;
    }

    @Override
    public List<EuroMillionsTicket> getTickets() {
         return dataFetchingService.fetchEuroMillionTickets();
    }
}
