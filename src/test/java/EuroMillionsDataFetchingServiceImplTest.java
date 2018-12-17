import my.lottery.client.LotteryClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import HashMapTest.TestPerson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EuroMillionsDataFetchingServiceImplTest {

    public static final String LOTTO_URL_PREFIX = "https://www.lottery.co.uk/lotto/results/archive-";

    private LotteryClient client;

    @Before
    public void setUp() {
        client = new LotteryClient();
    }

    @Test
    public void fetchAllYearsLottoData() throws IOException {
        for (int year = 1994; year <= 2018; year++) {
            Document doc = Jsoup.connect(LOTTO_URL_PREFIX + year).get();
            Elements resultsElements = doc.getElementsByClass("table lotto");
            resultsElements.toString();
            List<?> list = Stream.of(resultsElements).map(row -> {
                return row;
            }).collect(Collectors.toList());
            list.size();

        }

    }



}
