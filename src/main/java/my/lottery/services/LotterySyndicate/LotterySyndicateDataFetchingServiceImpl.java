package my.lottery.services.LotterySyndicate;

import lombok.extern.slf4j.Slf4j;
import my.lottery.client.LotteryClient;
import my.lottery.rest.dto.EuroMillionsTicketDto;
import my.lottery.services.DataFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static my.lottery.common.LotteryConstants.LOTTERY_SYNDICATE_URL;

@Slf4j
@Service("lotterySyndicateDataFetchingService")
public class LotterySyndicateDataFetchingServiceImpl implements DataFetchingService {

    private final LotteryClient lotteryClient;

    @Autowired
    public LotterySyndicateDataFetchingServiceImpl(LotteryClient nationalLotteryClient) {
        this.lotteryClient = nationalLotteryClient;
    }

    public List<EuroMillionsTicketDto> fetchEuroMillionTickets() {
        String result = this.lotteryClient.get(LOTTERY_SYNDICATE_URL);

        //List<EuroMillionsTicketDto> tickets =
        //        Arrays.stream(result.split("<div class=\"fl-col-content fl-node-content\">"))
        //              .filter(col -> col.contains("EuroMillions"))
        //              .flatMap(content -> Arrays.stream(content.split("<p>Ticket number:</p>")))
        //              .skip(1)
        //              .flatMap(line -> Arrays.stream(line.split("<li>Lucky ")))
        //              .skip(1)
        //              .map(lucky -> {
        //                  String[] uls = lucky.split("<ul>");
        //                  EuroMillionsTicketDto euroMillionsTicket = new EuroMillionsTicketDto();
        //                  Arrays.stream(uls)
        //                        .map(ul -> ul.replace("\n", ""))
        //                        .flatMap(ulInLine -> Arrays.stream(ulInLine.split("</ul>")))
        //                        .forEach(li -> {
        //                            if (li.matches("(<li>\\d\\d</li>){5}")) {
        //                                String[] numbers = li.replace("<li>", "").split("</li>");
        //                                euroMillionsTicket.setN1(Integer.valueOf(numbers[0]));
        //                                euroMillionsTicket.setN2(Integer.valueOf(numbers[1]));
        //                                euroMillionsTicket.setN3(Integer.valueOf(numbers[2]));
        //                                euroMillionsTicket.setN4(Integer.valueOf(numbers[3]));
        //                                euroMillionsTicket.setN5(Integer.valueOf(numbers[4]));
        //                            } else if (li.matches("(<li>\\d\\d</li>){2}")) {
        //                                String[] stars = li.replace("<li>", "").split("</li>");
        //                                euroMillionsTicket.setS1(Integer.valueOf(stars[0]));
        //                                euroMillionsTicket.setS2(Integer.valueOf(stars[1]));
        //                            }
        //                        });
        //                  return euroMillionsTicket;
        //              })
        //              .collect(Collectors.toList());

        List<EuroMillionsTicketDto> tickets = new ArrayList<>();
        Arrays.stream(result.split("<div class=\"fl-col-content fl-node-content\">"))
              .filter(col -> col.contains("EuroMillions"))
              .flatMap(content -> Arrays.stream(content.split("<p>Ticket number:</p>")))
              .skip(1)
              .flatMap(line -> Arrays.stream(line.split("<li>Lucky ")))
              .skip(1)
              .forEach(lucky -> {
                  String[] uls = lucky.split("<ul>");
                  EuroMillionsTicketDto euroMillionsTicket = new EuroMillionsTicketDto();
                  Arrays.stream(uls)
                        .map(ul -> ul.replace("\n", ""))
                        .flatMap(ulInLine -> Arrays.stream(ulInLine.split("</ul>")))
                        .forEach(li -> {
                            if (li.matches("(<li>\\d\\d</li>){5}")) {
                                String[] numbers = li.replace("<li>", "").split("</li>");
                                euroMillionsTicket.setN1(Integer.valueOf(numbers[0]));
                                euroMillionsTicket.setN2(Integer.valueOf(numbers[1]));
                                euroMillionsTicket.setN3(Integer.valueOf(numbers[2]));
                                euroMillionsTicket.setN4(Integer.valueOf(numbers[3]));
                                euroMillionsTicket.setN5(Integer.valueOf(numbers[4]));
                            } else if (li.matches("(<li>\\d\\d</li>){2}")) {
                                String[] stars = li.replace("<li>", "").split("</li>");
                                euroMillionsTicket.setS1(Integer.valueOf(stars[0]));
                                euroMillionsTicket.setS2(Integer.valueOf(stars[1]));
                                tickets.add(euroMillionsTicket);
                            }else if (li.matches("(<li>\\S{4}\\d{5}</li>){4}")){
                                String[] codes = li.replace("<li>", "").split("</li>");
                                tickets.get(tickets.size()-4).setCode(codes[0]);
                                tickets.get(tickets.size()-3).setCode(codes[1]);
                                tickets.get(tickets.size()-2).setCode(codes[2]);
                                tickets.get(tickets.size()-1).setCode(codes[3]);
                            }
                        });
              });

        //String[] lines = result.split("<p>Ticket number:</p>");
        //
        //List<EuroMillionsTicketDto> tickets = Arrays.stream(lines)
        //                             .skip(1)
        //                             .flatMap(line -> Arrays.stream(line.split("<li>Lucky ")))
        //                             .skip(1)
        //                             .map(lucky -> {
        //                                 String[] uls = lucky.split("<ul>");
        //                                 EuroMillionsTicketDto euroMillionsTicket = new EuroMillionsTicketDto();
        //                                 Arrays.stream(uls)
        //                                       .map(ul -> ul.replace("\n", ""))
        //                                       .flatMap(ulInLine -> Arrays.stream(ulInLine.split("</ul>")))
        //                                       .forEach(li -> {
        //                                           if (li.matches("(<li>\\d\\d</li>){5}")) {
        //                                               String[] numbers = li.replace("<li>", "").split("</li>");
        //                                               euroMillionsTicket.setN1(Integer.valueOf(numbers[0]));
        //                                               euroMillionsTicket.setN2(Integer.valueOf(numbers[1]));
        //                                               euroMillionsTicket.setN3(Integer.valueOf(numbers[2]));
        //                                               euroMillionsTicket.setN4(Integer.valueOf(numbers[3]));
        //                                               euroMillionsTicket.setN5(Integer.valueOf(numbers[4]));
        //                                           } else if (li.matches("(<li>\\d\\d</li>){2}")) {
        //                                               String[] stars = li.replace("<li>", "").split("</li>");
        //                                               euroMillionsTicket.setS1(Integer.valueOf(stars[0]));
        //                                               euroMillionsTicket.setS2(Integer.valueOf(stars[1]));
        //                                           }
        //                                       });
        //                                 return euroMillionsTicket;
        //                             })
        //                             .collect(Collectors.toList());
        return tickets;

    }

}
