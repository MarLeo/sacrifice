package models;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Tags {

    private Date realtime_start;

    private Date realtime_end;

    private String order_by;

    private String sort_order;

    private int count;

    private int offset;

    private int limit;

    private List<Tag> tags;
}
