package my.lottery.rest;

import my.lottery.rest.dto.EuroMillionsDrawResultDto;
import my.lottery.domain.services.drawresults.DrawResultManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("euro-millions")
public class NationalLotteryApi {

    @Autowired
    private DrawResultManagementService drawResultManagementService;

    @RequestMapping(method = RequestMethod.GET, value = "/historyResults")
    public List<EuroMillionsDrawResultDto> getHistoryResults() {
        drawResultManagementService.updateDrawResults();
        return drawResultManagementService.getHistoryResults().stream().map(ticket -> new EuroMillionsDrawResultDto(ticket))
                                          .collect(Collectors.toList());
    }

}
