package my.lottery.repository.impl;

import lombok.extern.slf4j.Slf4j;
import my.lottery.domain.data.EuroMillionsDrawResult;
import my.lottery.repository.DrawResultRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class DrawResultRepositoryInMemoryImpl implements DrawResultRepository {

    private List<EuroMillionsDrawResult> allDrawResults;

    public DrawResultRepositoryInMemoryImpl() {
        this.allDrawResults = new ArrayList<>();
    }

    @Override
    public List<EuroMillionsDrawResult> getAllDrawResults() {
        return allDrawResults;
    }

    @Override
    public void store(List<EuroMillionsDrawResult> tickets) {
        this.allDrawResults = tickets;
    }
}
