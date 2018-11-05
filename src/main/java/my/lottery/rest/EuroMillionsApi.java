package my.lottery.rest;

import my.lottery.model.EuroMillionsResult;
import my.lottery.services.EuroMillionsService;
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
    private EuroMillionsService euroMillionsService;

    @RequestMapping(method = RequestMethod.GET, value = "/getHistoryResults")
    public List<EuroMillionsResult> getHistoryResults() {
        return euroMillionsService.getHistoryResults();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getHistoryResults/{position}")
    public Map<Integer, Integer> getHistoryResultsOnPosition(@PathVariable("position")String position){
        return euroMillionsService.getHistoryResultsOnPosition(position);
    }

}