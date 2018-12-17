package my.lottery.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class EuroMillionResultModel {

    private int id;
    private Date drawDate;
    private int ball1;
    private int ball2;
    private int ball3;
    private int ball4;
    private int ball5;
    private int star1;
    private int star2;
    private String uKMillionaireMaker1;
    private String uKMillionaireMaker2;
    private int drawNumber;


}
