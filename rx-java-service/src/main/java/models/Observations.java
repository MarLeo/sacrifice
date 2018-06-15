package models;

import lombok.Data;

import java.util.List;

@Data
public class Observations {

    private long limit;

    private int count;

    private String realtime_end;

    private String observation_start;

    private String units;

    private List<Observation> observations;

    private String file_type;

    private String observation_end;

    private String realtime_start;

    private int output_type;

    private String sort_order;

    private int offset;

    private String order_by;
}
