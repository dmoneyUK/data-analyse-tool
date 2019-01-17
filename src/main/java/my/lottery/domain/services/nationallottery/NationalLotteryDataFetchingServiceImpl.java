package my.lottery.domain.services.nationallottery;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.LotteryClient;
import my.lottery.domain.assemblers.EuroMillionDrawResultAssembler;
import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.domain.services.DrawResultFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static my.lottery.common.LotteryConstants.EUROMILLION_URL;

@Slf4j
@Service("nationalLotteryDataFetchingService")
public class NationalLotteryDataFetchingServiceImpl implements DrawResultFetchingService {

    private final LotteryClient lotteryClient;
    private final EuroMillionDrawResultAssembler drawResultAssembler;

    @Autowired
    public NationalLotteryDataFetchingServiceImpl(LotteryClient nationalLotteryClient, EuroMillionDrawResultAssembler drawResultAssembler) {
        this.lotteryClient = nationalLotteryClient;
        this.drawResultAssembler = drawResultAssembler;
    }

    public List<EuroMillionsDrawResult> fetchEuroMillionDrawResults() {
        String response = this.lotteryClient.get(EUROMILLION_URL);
        return drawResultAssembler.toDomain(response);
    }
}
