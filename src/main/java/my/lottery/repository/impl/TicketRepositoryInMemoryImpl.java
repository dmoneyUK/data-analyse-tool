package my.lottery.repository.impl;

import lombok.extern.slf4j.Slf4j;
import my.lottery.domain.data.EuroMillionsTicket;
import my.lottery.repository.TicketRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class TicketRepositoryInMemoryImpl implements TicketRepository {

    private List<EuroMillionsTicket> allTickets;

    public TicketRepositoryInMemoryImpl(){
        this.allTickets = new ArrayList<>();
    }

    @Override
    public List<EuroMillionsTicket> getAllTickets() {
        return allTickets;
    }

    @Override
    public void store(List<EuroMillionsTicket> tickets) {
        this.allTickets = tickets;
    }
}
