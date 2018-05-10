package models;

import lombok.Data;

@Data
public class Repository {

    private String name;
    private String description;

    public Repository(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
