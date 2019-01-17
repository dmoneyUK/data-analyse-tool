package my.lottery.domain.services.drawresults.impl;

import lombok.extern.slf4j.Slf4j;
import my.lottery.domain.services.drawresults.DrawResultFetchingService;
import my.lottery.domain.services.drawresults.DrawResultManagementService;
import my.lottery.repository.DrawResultRepository;
import my.lottery.repository.TicketRepository;
import my.lottery.domain.data.EuroMillionsDrawResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DrawResultManagementServiceImpl implements DrawResultManagementService {
    private final DrawResultFetchingService euroMillionsDataFetchingService;
    private final DrawResultRepository drawResultRepository;

    @Autowired
    public DrawResultManagementServiceImpl(final DrawResultRepository drawResultRepository,
                                           final DrawResultFetchingService euroMillionsDataFetchingService) {
        this.drawResultRepository = drawResultRepository;
        this.euroMillionsDataFetchingService = euroMillionsDataFetchingService;

    }



    @Override
    public List<EuroMillionsDrawResult> getHistoryResults() {
        return drawResultRepository.getAllDrawResults();

    }

    @Override
    public void updateDrawResults() {
        List<EuroMillionsDrawResult> drawResults = euroMillionsDataFetchingService.fetchEuroMillionDrawResults();
        drawResultRepository.store(drawResults);
    }

}

