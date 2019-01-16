package my.lottery.services.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EuroMillionsTicket {

    private int b1;
    private int b2;
    private int b3;
    private int b4;
    private int b5;
    private int s1;
    private int s2;
    private String code;
    private LocalDate drawDate;
}
