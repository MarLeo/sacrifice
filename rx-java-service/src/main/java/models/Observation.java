package models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Observation {

    private String realtime_start;

    private String realtime_end;

    private String date;

    private BigDecimal value;
}
