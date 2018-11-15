package my.lottery.services;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.NationLotteryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static my.lottery.common.EuroMillionsUtils.EUROMILLION_URL;
import static my.lottery.common.EuroMillionsUtils.LOTTO_URL;

@Slf4j
@Service
public class DataFetchingServiceImpl {

    private final NationLotteryClient nationLotteryClient;

    @Autowired
    public DataFetchingServiceImpl(NationLotteryClient nationalLotteryClient) {
        this.nationLotteryClient = nationalLotteryClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    private String fetchLatestEuroMillionResults() {
        String result = this.nationLotteryClient.get(EUROMILLION_URL);
        return result;
    }

    @EventListener(ApplicationReadyEvent.class)
    private String fetchLottoResults() {
        String result = this.nationLotteryClient.get(LOTTO_URL);
        return result;
    }
}
