package my.lottery.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EuroMillionsResult {

    private int n1;
    private int n2;
    private int n3;
    private int n4;
    private int n5;
    private int s1;
    private int s2;

}
