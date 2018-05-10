package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class Contributor {

    @SerializedName("login")
    private String name;

    private Double contributions;


    public Contributor(String name, Double contributions) {
        this.name = name;
        this.contributions = contributions;
    }
}
