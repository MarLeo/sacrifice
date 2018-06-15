package models;


import lombok.Data;

@Data
public class Tag {

    private String name;

    private String group_id;

    private String notes;

    private String created;

    private int popularity;

    private int series_count;
}
