package my.lottery.rest;

import my.lottery.model.EuroMillionsResult;
import my.lottery.services.EuroMillionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EuroMillionsApi {

    @Autowired
    private EuroMillionsService euroMillionsService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllResults")
    public List<EuroMillionsResult> getAllResults() {
        return euroMillionsService.getAllResults();
    }

}
