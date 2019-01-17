package my.lottery.domain.services.nationallottery;

import lombok.extern.slf4j.Slf4j;
import my.lottery.repository.EuroMillionsDataRepository;
import my.lottery.domain.services.DrawResultFetchingService;
import my.lottery.domain.services.DrawResultManagementService;
import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.domain.data.EuroMillionsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static my.lottery.common.LotteryConstants.B1;
import static my.lottery.common.LotteryConstants.B2;
import static my.lottery.common.LotteryConstants.B3;
import static my.lottery.common.LotteryConstants.B4;
import static my.lottery.common.LotteryConstants.B5;
import static my.lottery.common.LotteryConstants.S1;
import static my.lottery.common.LotteryConstants.S2;

@Slf4j
@Service
public class DrawResultManagementServiceImpl implements DrawResultManagementService {
    private final DrawResultFetchingService euroMillionsDataFetchingService;
    private final EuroMillionsDataRepository euroMillionsDataRepository;

    @Autowired
    public DrawResultManagementServiceImpl(final EuroMillionsDataRepository euroMillionsDataRepository,
                                           final DrawResultFetchingService euroMillionsDataFetchingService) {
        this.euroMillionsDataRepository = euroMillionsDataRepository;
        this.euroMillionsDataFetchingService = euroMillionsDataFetchingService;

    }

    @Override
    public List<EuroMillionsDrawResult> getHistoryResults() {

        return euroMillionsDataFetchingService.fetchEuroMillionDrawResults();

    }

}

