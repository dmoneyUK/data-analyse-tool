package my.lottery.domain.assemblers;

import lombok.extern.slf4j.Slf4j;
import my.lottery.domain.data.EuroMillionsDrawResult;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class EuroMillionDrawResultAssembler {
    static private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public List<EuroMillionsDrawResult> toDomain(String html) {
        try {
            List<EuroMillionsDrawResult> results = Arrays.stream(html.split("\n"))
                                                         .skip(1)
                                                         .map(line -> line.split(","))
                                                         .map(fields -> {

                                                             return EuroMillionsDrawResult
                                                                     .builder()
                                                                     .drawDate(LocalDate.parse(fields[0], dateTimeFormatter))
                                                                     .b1(Integer.valueOf(fields[1]))
                                                                     .b2(Integer.valueOf(fields[2]))
                                                                     .b3(Integer.valueOf(fields[3]))
                                                                     .b4(Integer.valueOf(fields[4]))
                                                                     .b5(Integer.valueOf(fields[5]))
                                                                     .s1(Integer.valueOf(fields[6]))
                                                                     .s2(Integer.valueOf(fields[7]))
                                                                     .codes(IntStream.range(8, fields.length - 2)
                                                                                     .mapToObj(i -> fields[i].replace("\"", ""))
                                                                                     .collect(Collectors.toList()))
                                                                     .build();

                                                         })
                                                         .collect(Collectors.toList());

            return results;

        } catch (Exception e) {
            log.error("Error occurred: {}", e);
            return Collections.emptyList();
        }
    }
}
