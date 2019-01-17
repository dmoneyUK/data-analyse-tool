package my.lottery.repository;

import my.lottery.domain.data.EuroMillionsTicket;

import java.util.List;

public interface TicketRepository {

     List<EuroMillionsTicket> getAllTickets();

     void store(List<EuroMillionsTicket> tickets);

}
