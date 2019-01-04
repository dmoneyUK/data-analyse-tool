package my.lottery.services.EuroMillions.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EuroMillionDrawResultData {
    private LocalDate drawDate;
}
