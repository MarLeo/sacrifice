package models;


import lombok.Data;

import java.util.List;

@Data
public class Seriess {

    private String realtime_start;

    private String realtime_end;

    private String order_by;

    private String sort_order;

    private int count;

    private int offset;

    private int limit;

    private List<Series> seriess;
}
