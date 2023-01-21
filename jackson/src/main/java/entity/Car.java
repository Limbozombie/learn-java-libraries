package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@Getter
public class Car {

    private String color;
    private String type;
    @JsonProperty("apple")
    private String banana;

//    @JsonProperty("isSelected")
    private boolean isSelected;

}
