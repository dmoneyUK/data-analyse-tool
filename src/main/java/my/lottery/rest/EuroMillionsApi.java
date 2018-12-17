package my.lottery.rest;

import my.lottery.rest.dto.EuroMillionsResult;
import my.lottery.services.NationalLotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EuroMillionsApi {

    @Autowired
    private NationalLotteryService euroMillionsService;

    @RequestMapping(method = RequestMethod.GET, value = "/historyResults")
    public List<EuroMillionsResult> getHistoryResults() {
        return euroMillionsService.getHistoryResults();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/historyResults/{position}")
    public Map<Integer, Integer> getHistoryResultsOnPosition(@PathVariable("position") String position) {
        return euroMillionsService.getHistoryResultsInPosition(position);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/luckydip")
    public EuroMillionsResult getLuckyDip() {
        return euroMillionsService.getLuckyDip();
    }

}
