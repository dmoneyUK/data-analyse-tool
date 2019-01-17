package my.lottery.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import my.lottery.domain.data.EuroMillionsTicket;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EuroMillionsTicketDto {

    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private int n5;
    private int s1;
    private int s2;
    private String code;
    private LocalDate drawDate;

    public EuroMillionsTicketDto(EuroMillionsTicket ticket){
        n1 = ticket.getB1();
        n2 = ticket.getB2();
        n3 = ticket.getB3();
        n4 = ticket.getB4();
        n5 = ticket.getB5();
        s1 = ticket.getS1();
        s1 = ticket.getS2();
        code = ticket.getCode();
        drawDate = ticket.getDrawDate();

    }

}
