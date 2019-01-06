package my.lottery.repository.impl;

import lombok.extern.slf4j.Slf4j;
import my.lottery.repository.EuroMillionsDataRepository;
import my.lottery.services.data.EuroMillionsTicket;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class EuroMillionsDataRepositoryCsvImpl implements EuroMillionsDataRepository {

    @Override
    public List<EuroMillionsTicket> getHistoryResults() {
        return readHistoryResultFromFile("HistoryResult.csv");
    }

    private List<EuroMillionsTicket> readHistoryResultFromFile(String fileName) {

        try {
            return Files.lines(Paths.get(getClass().getClassLoader().getResource("HistoryResult.csv").toURI()))
                        .skip(1)
                        .map(line -> line.split(","))
                        .map(fields -> new EuroMillionsTicket(Integer.valueOf(fields[5].trim()), Integer.valueOf(fields[6].trim()),
                                                                 Integer.valueOf(fields[7].trim()), Integer.valueOf(fields[8].trim()),
                                                                 Integer.valueOf(fields[9].trim()), Integer.valueOf(fields[10].trim()),
                                                                 Integer.valueOf(fields[11].trim()),null, null))
                        .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error occurred: {}", e);
            return Collections.emptyList();
        }
    }
}
