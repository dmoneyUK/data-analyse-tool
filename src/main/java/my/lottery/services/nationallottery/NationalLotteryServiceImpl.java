package my.lottery.services.nationallottery;

import lombok.extern.slf4j.Slf4j;
import my.lottery.repository.EuroMillionsDataRepository;
import my.lottery.services.DataFetchingService;
import my.lottery.services.NationalLotteryService;
import my.lottery.services.data.EuroMillionsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import static my.lottery.common.LotteryConstants.B1;
import static my.lottery.common.LotteryConstants.B2;
import static my.lottery.common.LotteryConstants.B3;
import static my.lottery.common.LotteryConstants.B4;
import static my.lottery.common.LotteryConstants.B5;
import static my.lottery.common.LotteryConstants.S1;
import static my.lottery.common.LotteryConstants.S2;

@Slf4j
@Service
public class NationalLotteryServiceImpl implements NationalLotteryService {
    private final DataFetchingService euroMillionsDataFetchingService;
    private final EuroMillionsDataRepository euroMillionsDataRepository;

    private List<Integer> n1List;
    private List<Integer> n2List;
    private List<Integer> n3List;
    private List<Integer> n4List;
    private List<Integer> n5List;
    private List<Integer> s1List;
    private List<Integer> s2List;

    private SecureRandom rand;

    @Autowired
    public NationalLotteryServiceImpl(final EuroMillionsDataRepository euroMillionsDataRepository,
                                      final @Qualifier("nationalLotteryDataFetchingService") DataFetchingService euroMillionsDataFetchingService) {
        this.euroMillionsDataRepository = euroMillionsDataRepository;
        this.euroMillionsDataFetchingService = euroMillionsDataFetchingService;
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
    public List<EuroMillionsTicket> getHistoryResults() {

        //TODO call http://lottery.merseyworld.com/cgi-bin/lottery?days=20&Machine=Z&Ballset=0&order=0&show=1&year=0&display=NoTables
        euroMillionsDataFetchingService.fetchEuroMillionTickets();

        return euroMillionsDataRepository.getHistoryResults();
    }

    @Override
    public Map<Integer, Integer> getHistoryResultsInPosition(String position) {
        Map<Integer, Integer> valueCount = getHistoryResults()
                .stream()
                .map(r -> {
                    switch (position.toUpperCase()) {
                        case B1:
                            return r.getB1();
                        case B2:
                            return r.getB2();
                        case B3:
                            return r.getB3();
                        case B4:
                            return r.getB4();
                        case B5:
                            return r.getB5();
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
    public EuroMillionsTicket getLuckyDip() {
        EuroMillionsTicket luckyDip = new EuroMillionsTicket();
        n1List = getMostLikelyNumbersInPosition(B1);
        n2List = getMostLikelyNumbersInPosition(B2);
        n3List = getMostLikelyNumbersInPosition(B3);
        n4List = getMostLikelyNumbersInPosition(B4);
        n5List = getMostLikelyNumbersInPosition(B5);
        s1List = getMostLikelyNumbersInPosition(S1);
        s2List = getMostLikelyNumbersInPosition(S2);

        luckyDip.setB1(getLuckyNumberInPosition(n1List));
        luckyDip.setB2(getLuckyNumberInPosition(n2List));
        luckyDip.setB3(getLuckyNumberInPosition(n3List));
        luckyDip.setB4(getLuckyNumberInPosition(n4List));
        luckyDip.setB5(getLuckyNumberInPosition(n5List));
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

