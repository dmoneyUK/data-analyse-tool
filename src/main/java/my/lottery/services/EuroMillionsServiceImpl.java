package my.lottery.services;

import lombok.extern.slf4j.Slf4j;
import my.lottery.model.EuroMillionsResult;
import my.lottery.repository.EuroMillionsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
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

    private EuroMillionsDataRepository euroMillionsDataRepository;

    private List<Integer> n1List;
    private List<Integer> n2List;
    private List<Integer> n3List;
    private List<Integer> n4List;
    private List<Integer> n5List;
    private List<Integer> s1List;
    private List<Integer> s2List;

    private SecureRandom rand;

    @Autowired
    public EuroMillionsServiceImpl(EuroMillionsDataRepository euroMillionsDataRepository) {
        this.euroMillionsDataRepository = euroMillionsDataRepository;
        rand = new SecureRandom();

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
    public Map<Integer, Integer> getHistoryResultsInPosition(String position) {
        Map<Integer, Integer> valueCount = getHistoryResults()
                .stream()
                .map(r -> {
                    switch (position.toUpperCase()) {
                        case N1:
                            return r.getN1();
                        case N2:
                            return r.getN2();
                        case N3:
                            return r.getN3();
                        case N4:
                            return r.getN4();
                        case N5:
                            return r.getN5();
                        case S1:
                            return r.getS1();
                        case S2:
                            return r.getS2();
                    }
                    return null;
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(reverseOrder()))
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        return valueCount;
    }

    @Override
    public EuroMillionsResult getLuckyDip() {
        EuroMillionsResult luckyDip = new EuroMillionsResult();
        n1List = getMostLikelyNumbersInPosition(N1);
        n2List = getMostLikelyNumbersInPosition(N2);
        n3List = getMostLikelyNumbersInPosition(N3);
        n4List = getMostLikelyNumbersInPosition(N4);
        n5List = getMostLikelyNumbersInPosition(N5);
        s1List = getMostLikelyNumbersInPosition(S1);
        s2List = getMostLikelyNumbersInPosition(S2);

        luckyDip.setN1(getLuckyNumberInPosition(n1List));
        luckyDip.setN2(getLuckyNumberInPosition(n2List));
        luckyDip.setN3(getLuckyNumberInPosition(n3List));
        luckyDip.setN4(getLuckyNumberInPosition(n4List));
        luckyDip.setN5(getLuckyNumberInPosition(n5List));
        luckyDip.setS1(getLuckyNumberInPosition(s1List));
        luckyDip.setS2(getLuckyNumberInPosition(s2List));
        return luckyDip;
    }

    private Integer getLuckyNumberInPosition(List<Integer> list) {
        return list.get(rand.nextInt(list.size()));
    }

    private List<Integer> getMostLikelyNumbersInPosition(String n1) {
        return getHistoryResultsInPosition(n1).entrySet().stream().filter(e -> e.getValue() > 40).map(e -> e.getKey()).collect(toList());
    }
}

