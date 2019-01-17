package my.lottery.domain.services.checking;

import my.lottery.domain.data.EuroMillionsTicket;

import java.util.List;

public interface CheckingService {
    List<EuroMillionsTicket> checkWinnings(String memberid);
}
