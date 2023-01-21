package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleBean {
    @JsonProperty("Apple")
    private int x = 1;
    private int y = 2;
}
