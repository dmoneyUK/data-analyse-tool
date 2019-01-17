package my.lottery.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import my.lottery.domain.data.EuroMillionsDrawResult;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class EuroMillionsDrawResultDto {

    private int b1;
    private int b2;
    private int b3;
    private int b4;
    private int b5;
    private int s1;
    private int s2;
    private List<String> codes;
    private LocalDate drawDate;


    public EuroMillionsDrawResultDto(EuroMillionsDrawResult drawResult){
        b1 = drawResult.getB1();
        b2 = drawResult.getB2();
        b3 = drawResult.getB3();
        b4 = drawResult.getB4();
        b5 = drawResult.getB5();
        s1 = drawResult.getS1();
        s2 = drawResult.getS2();
        codes = drawResult.getCodes();
        drawDate = drawResult.getDrawDate();


    }

}
