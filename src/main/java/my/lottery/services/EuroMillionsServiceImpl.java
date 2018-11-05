package my.lottery.services;

import lombok.extern.slf4j.Slf4j;
import my.lottery.model.EuroMillionsResult;
import my.lottery.repository.EuroMillionsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.lottery.common.EuroMillionsUtils.N1;
import static my.lottery.common.EuroMillionsUtils.N2;
import static my.lottery.common.EuroMillionsUtils.N3;
import static my.lottery.common.EuroMillionsUtils.N4;
import static my.lottery.common.EuroMillionsUtils.N5;
import static my.lottery.common.EuroMillionsUtils.S1;
import static my.lottery.common.EuroMillionsUtils.S2;

@Service
@Slf4j
public class EuroMillionsServiceImpl implements EuroMillionsService {

    @Autowired
    private EuroMillionsDataRepository euroMillionsDataRepository;

    @Override
    public List<EuroMillionsResult> getAllResults() {
        //TODO call http://lottery.merseyworld.com/cgi-bin/lottery?days=20&Machine=Z&Ballset=0&order=0&show=1&year=0&display=NoTables
        return euroMillionsDataRepository.getHistoryResults();
    }

    //private Map<String, List<Integer>> getResultMap() {
    //    Map<String, List<Integer>> resultMap = new HashMap();
    //    try {
    //        resultMap.put(N1, new ArrayList<Integer>());
    //        resultMap.put(N2, new ArrayList<Integer>());
    //        resultMap.put(N3, new ArrayList<Integer>());
    //        resultMap.put(N4, new ArrayList<Integer>());
    //        resultMap.put(N5, new ArrayList<Integer>());
    //        resultMap.put(S1, new ArrayList<Integer>());
    //        resultMap.put(S2, new ArrayList<Integer>());

            //Files.readAllLines(Paths.get(fileName))
            //     .stream()
            //     .map(line -> line.split(","))
            //     .forEach(fields -> {
            //         resultMap.get(N1).add((T) fields[5]);
            //         resultMap.get(N2).add((T) fields[6]);
            //         resultMap.get(N3).add((T) fields[7]);
            //         resultMap.get(N4).add((T) fields[8]);
            //         resultMap.get(N5).add((T) fields[9]);
            //         resultMap.get(S1).add((T) fields[10]);
            //         resultMap.get(S2).add((T) fields[11]);
            //     });

    //    } catch (IOException e) {
    //        log.error("Error occurred: {}", e);
    //        return Collections.emptyMap();
    //    }
    //}
}

