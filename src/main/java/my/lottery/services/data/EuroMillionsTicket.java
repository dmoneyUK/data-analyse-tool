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

    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private int n5;
    private int s1;
    private int s2;
    private String code;
    private LocalDate drawDate;
}
