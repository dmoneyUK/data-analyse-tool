package my.lottery.services.nationallottery;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.LotteryClient;
import my.lottery.services.DataFetchingService;
import my.lottery.services.data.EuroMillionsDrawResult;
import my.lottery.services.data.EuroMillionsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        String response = this.lotteryClient.get(EUROMILLION_URL);
        Arrays.stream(response.split("list_table list_table_presentation table_row_"))
              .skip(1)
              .map(table -> {
                       EuroMillionsDrawResult drawResult = new EuroMillionsDrawResult();
                       Arrays.stream(table.split("<span class=\"table_cell_block\">"))
                             .skip(1)
                             .map(spanBegining -> spanBegining.substring(0, spanBegining.indexOf("</span>")).replace("\n", "").trim())
                             .forEach(span -> {
                                 if (span.matches("(Tue|Fri) \\d\\d \\S\\S\\S \\d\\d\\d\\d")) {
                                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E d MMM yyyy");
                                     LocalDate drawDate = LocalDate.parse(span, formatter);
                                     drawResult.setDrawDate(drawDate);
                                     return;
                                 } else if (span.matches("\\d\\d - \\d\\d - \\d\\d - \\d\\d - \\d\\d")) {
                                     String[] balls = span.split(" - ");
                                     drawResult.setN1(Integer.valueOf(balls[0]));
                                     drawResult.setN2(Integer.valueOf(balls[1]));
                                     drawResult.setN3(Integer.valueOf(balls[2]));
                                     drawResult.setN4(Integer.valueOf(balls[3]));
                                     drawResult.setN5(Integer.valueOf(balls[4]));
                                     return;
                                 } else if (span.matches("\\d\\d - \\d\\d")) {
                                     String[] stars = span.split(" - ");
                                     drawResult.setS1(Integer.valueOf(stars[0]));
                                     drawResult.setS2(Integer.valueOf(stars[1]));
                                     return;
                                 } else if (span.matches("((\\S{4}\\d{5})(\\s*))*")) {

                                     Arrays.stream(span.split("\\s+"))
                                           .forEach(code -> drawResult.getCodes().add(code));
                                 }
                             });

                       return drawResult;
                   }
              ).collect(Collectors.toList());

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
