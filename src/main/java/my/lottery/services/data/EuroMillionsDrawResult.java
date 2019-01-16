package my.lottery.services.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EuroMillionsDrawResult {

    private int b1;
    private int b2;
    private int b3;
    private int b4;
    private int b5;
    private int s1;
    private int s2;
    private List<String> codes;
    private LocalDate drawDate;

    public EuroMillionsDrawResult(){
        this.codes = new ArrayList<>();
    }

}
