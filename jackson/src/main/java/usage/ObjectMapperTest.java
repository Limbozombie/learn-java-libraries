package usage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import entity.Car;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ObjectMapperTest {
    ObjectMapper mapper;

    @Before
    public void init() {
        mapper = new JsonMapper();
    }


    @Test
    public void basicUsage() throws IOException {


        Car car = new Car();
//        car.setColor("yellow");
//        car.setType("renault");
        mapper.writeValue(new File("target/car.json"), car);
        String carAsString = mapper.writeValueAsString(car);
        System.out.println(carAsString);


        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        car = mapper.readValue(json, Car.class);
        System.out.println("11" + car);
    }

    @Test
    public void JsonNode() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = mapper.readTree(json);
        String color = jsonNode.get("color").asText();
        System.out.println(color);
    }

    @Test
    @SneakyThrows
    public void JSONArrayString2Obj() {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = mapper.readValue(jsonCarArray, new TypeReference<>() {
        });
        System.out.println(listCar);
    }

    @Test
    @SneakyThrows
    public void JSON2map() {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map = mapper.readValue(json, new TypeReference<>() {
        });
        System.out.println(map);

    }

    @Test
    @SneakyThrows
    public void feature() {
        String jsonString
                = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        Car car = mapper.readValue(jsonString, Car.class);
        System.out.println(car);

    }

    @Test
    @SneakyThrows
    public void jsonProperty() {
        String jsonString
                = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"apple\" : \"isApple\" ,\"isSelected\":true}";

        Car car = mapper.readValue(jsonString, Car.class);
        System.out.println(car);

        String valueAsString = mapper.writeValueAsString(car);
        System.out.println(valueAsString);

    }


}
