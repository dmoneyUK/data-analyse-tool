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

    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private int n5;
    private int s1;
    private int s2;
    private List<String> codes;
    private LocalDate drawDate;

    public EuroMillionsDrawResult(){
        this.codes = new ArrayList<>();
    }

}
