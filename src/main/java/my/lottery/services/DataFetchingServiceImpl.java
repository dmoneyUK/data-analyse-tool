package my.lottery.services;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.NationLotteryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static my.lottery.common.EuroMillionsUtils.NATION_LOTTERY_URI;

@Slf4j
@Service
public class DataFetchingServiceImpl{

    private final NationLotteryClient nationLotteryClient;

    @Autowired
    public DataFetchingServiceImpl(NationLotteryClient nationalLotteryClient){
        this.nationLotteryClient = nationalLotteryClient;
    }
    @EventListener(ApplicationReadyEvent.class)
    private String fetchLatestResults() {
        String result = this.nationLotteryClient.get(NATION_LOTTERY_URI);
        return result;
    }
}
