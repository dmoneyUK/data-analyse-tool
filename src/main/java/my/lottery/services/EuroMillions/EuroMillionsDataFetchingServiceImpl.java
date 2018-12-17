package my.lottery.services.EuroMillions;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.LotteryClient;
import my.lottery.services.DataFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static my.lottery.common.LotteryConstants.EUROMILLION_URL;

@Slf4j
@Service
public class EuroMillionsDataFetchingServiceImpl implements DataFetchingService{

    private final LotteryClient lotteryClient;

    @Autowired
    public EuroMillionsDataFetchingServiceImpl(LotteryClient nationalLotteryClient) {
        this.lotteryClient = nationalLotteryClient;
    }

    public String fetchLatestEuroMillionResults() {
        String result = this.lotteryClient.get(EUROMILLION_URL);
        return result;
    }
}
