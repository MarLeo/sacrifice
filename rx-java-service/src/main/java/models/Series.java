package models;


import lombok.Data;

import java.util.Date;

@Data
public class Series {

    private String id;

    private String realtime_start;

    private String realtime_end;

    private String title;

    private Date observation_start;

    private Date observation_end;

    private String frequency;

    private String frequency_short;

    private String units;

    private String units_short;

    private String seasonal_adjustment;

    private String seasonal_adjustment_short;

    private String last_updated;

    private int popularity;

    private int group_popularity;

    private String notes;
}
