package my.lottery.domain.assemblers;

import lombok.extern.slf4j.Slf4j;
import my.lottery.domain.data.EuroMillionsTicket;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
public class EuroMillionTicketAssembler {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E d MMM yyyy");

    public List<EuroMillionsTicket> toDomain(String html) {
        List<EuroMillionsTicket> tickets = new ArrayList<>();
        Arrays.stream(html.split("<div class=\"fl-col-content fl-node-content\">"))
              .filter(col -> col.contains("EuroMillions"))
              .flatMap(content -> Arrays.stream(content.split("<ul>")))
              .filter(ul -> ul.startsWith("\n<li>"))
              .flatMap(ul -> Arrays.stream(ul.replace("\n", "").split("</ul>")))
              .forEach(li -> {
                  if (li.matches("(<li>\\d\\d</li>){5}")) {
                      EuroMillionsTicket euroMillionsTicket = new EuroMillionsTicket();
                      String[] numbers = li.replace("<li>", "").split("</li>");
                      euroMillionsTicket.setB1(Integer.valueOf(numbers[0]));
                      euroMillionsTicket.setB2(Integer.valueOf(numbers[1]));
                      euroMillionsTicket.setB3(Integer.valueOf(numbers[2]));
                      euroMillionsTicket.setB4(Integer.valueOf(numbers[3]));
                      euroMillionsTicket.setB5(Integer.valueOf(numbers[4]));
                      tickets.add(euroMillionsTicket);

                  } else if (li.matches("(<li>\\d\\d</li>){2}")) {
                      EuroMillionsTicket euroMillionsTicket = tickets.get(tickets.size() - 1);
                      String[] stars = li.replace("<li>", "").split("</li>");
                      euroMillionsTicket.setS1(Integer.valueOf(stars[0]));
                      euroMillionsTicket.setS2(Integer.valueOf(stars[1]));

                  } else if (li.matches("(<li>\\S{4}\\d{5}</li>)+")) {
                      String[] codes = li.replace("<li>", "").split("</li>");

                      IntStream.range(0, codes.length)
                               .forEach(i -> {
                                   tickets.get(tickets.size() - 1 - i).setCode(codes[i]);
                               });
                  } else if (li.matches("<li>(Tue|Fri) \\d\\d \\S\\S\\S \\d\\d\\d\\d</li>")) {
                      String drawDateLine = li.replace("<li>", "").replace("</li>", "");
                      LocalDate drawDate = LocalDate.parse(drawDateLine, formatter);

                      int i = tickets.size() - 1;
                      while (i >= 0 && tickets.get(i).getDrawDate() == null) {
                          tickets.get(i).setDrawDate(drawDate);
                          i--;
                      }
                  }
              });

        return tickets;

    }
}
