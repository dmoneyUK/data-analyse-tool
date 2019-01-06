package my.lottery.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

}
