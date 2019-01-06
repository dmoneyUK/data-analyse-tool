package my.lottery.services.nationallottery;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.LotteryClient;
import my.lottery.services.DataFetchingService;
import my.lottery.services.data.EuroMillionsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static my.lottery.common.LotteryConstants.EUROMILLION_URL;

@Slf4j
@Service("nationalLotteryDataFetchingService")
public class NationalLotteryDataFetchingServiceImpl implements DataFetchingService {

    private final LotteryClient lotteryClient;

    @Autowired
    public NationalLotteryDataFetchingServiceImpl(LotteryClient nationalLotteryClient) {
        this.lotteryClient = nationalLotteryClient;
    }

    public List<EuroMillionsTicket> fetchEuroMillionTickets() {
        String result = this.lotteryClient.get(EUROMILLION_URL);

        String[] lines = result.split("<a class=\"title\" href=\"/results/");

        Arrays.stream(lines).filter(line -> {
            return line.startsWith("<a class=\"title\" href=\"/results/");
        }).map(line -> {

            String date = getDrawDate(line);

            Arrays.stream(line.split("\n"))
                  .filter(tag -> {
                      return tag.trim().startsWith("<li class=\"new ball\">");
                  }).map(b -> {
                return getNumber(line);
            });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy", Locale.ENGLISH);
            return new EuroMillionsTicket();

        }).collect(Collectors.toList());
        return Collections.emptyList();
    }

    private String getNumber(String line) {
        Matcher matcher = Pattern.compile("\\d[1-2]").matcher(line);
        matcher.find();
        return matcher.group(0);
    }

    private String getDrawDate(String line) {
        Matcher matcher = Pattern.compile("\\d{2}-\\d{2}-\\d{4}").matcher(line);
        matcher.find();
        return matcher.group(0);
    }

}