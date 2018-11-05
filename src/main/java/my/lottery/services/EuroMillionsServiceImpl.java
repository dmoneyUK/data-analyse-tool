package my.lottery.services;

import lombok.extern.slf4j.Slf4j;
import my.lottery.model.EuroMillionsResult;
import my.lottery.repository.EuroMillionsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

@Service
@Slf4j
public class EuroMillionsServiceImpl implements EuroMillionsService {

    private EuroMillionsDataRepository euroMillionsDataRepository;

    private List<Integer> n1List;
    private List<Integer> n2List;
    private List<Integer> n3List;
    private List<Integer> n4List;
    private List<Integer> n5List;
    private List<Integer> s1List;
    private List<Integer> s2List;

    @Autowired
    public EuroMillionsServiceImpl(EuroMillionsDataRepository euroMillionsDataRepository) {
        this.euroMillionsDataRepository = euroMillionsDataRepository;
        n1List = new ArrayList<>();
        n2List = new ArrayList<>();
        n3List = new ArrayList<>();
        n4List = new ArrayList<>();
        n5List = new ArrayList<>();
        s1List = new ArrayList<>();
        s2List = new ArrayList<>();
    }

    @Override
    public List<EuroMillionsResult> getHistoryResults() {
        //TODO call http://lottery.merseyworld.com/cgi-bin/lottery?days=20&Machine=Z&Ballset=0&order=0&show=1&year=0&display=NoTables
        return euroMillionsDataRepository.getHistoryResults();
    }

    @Override
    public Map<Integer, Integer> getHistoryResultsOnPosition(String position) {
        Map<Integer, Integer> valueCount = getHistoryResults()
                .stream()
                .map(r -> {
                    switch (position.toUpperCase()) {
                        case "N1":
                            return r.getN1();
                        case "N2":
                            return r.getN2();
                        case "N3":
                            return r.getN3();
                        case "N4":
                            return r.getN4();
                        case "N5":
                            return r.getN5();
                        case "S1":
                            return r.getS1();
                        case "S2":
                            return r.getS2();
                    }
                    return null;
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 40)
                .sorted(Map.Entry.comparingByValue(reverseOrder()))
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        return valueCount;
    }

    private void getResultsOfEachNumber() {

        getHistoryResults().forEach(result -> {
            n1List.add(result.getN1());
            n2List.add(result.getN2());
            n3List.add(result.getN3());
            n4List.add(result.getN4());
            n5List.add(result.getN5());
            s1List.add(result.getS1());
            s2List.add(result.getS2());
        });
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

