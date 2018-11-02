package my.lottery.repository;

import lombok.extern.slf4j.Slf4j;
import my.lottery.model.EuroMillionsResult;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
public class EuroMillionsDataRepositoryCsvImpl implements EuroMillionsDataRepository {

    @Override
    public List<EuroMillionsResult> getHistoryResults() {
        return readHistoryResultFromFile("HistoryResult.csv");
    }

    private List<EuroMillionsResult> readHistoryResultFromFile(String fileName) {

        try {
            //return Files.lines(Paths.get(getClass().getClassLoader().getResource("HistoryResult.csv").toURI()))
            return Files.lines(Paths.get(getClass().getClassLoader().getResource("HistoryResult.csv").toURI()))
                        .skip(1)
                        .map(line -> line.split(","))
                        .map(fields -> new EuroMillionsResult(Integer.valueOf(fields[5].trim()), Integer.valueOf(fields[6].trim()),
                                                              Integer.valueOf(fields[7].trim()), Integer.valueOf(fields[8].trim()),
                                                              Integer.valueOf(fields[9].trim()), Integer.valueOf(fields[10].trim()),
                                                              Integer.valueOf(fields[11].trim())))
                        .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error occurred: {}", e);
            return Collections.emptyList();
        }
    }
}
