package usage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import entity.Person;
import entity.SimpleBean;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class XmlMapperTest {
    ObjectMapper mapper;

    @Before
    public void init() {
        mapper = new XmlMapper();
    }

    @Test
    @SneakyThrows
    public void usage() {
        SimpleBean value = new SimpleBean();
        String xml = mapper.writeValueAsString(value);
        System.out.println(xml);

        mapper.writeValue(new File("target/simple_bean.xml"), value);
    }

    @Test
    @SneakyThrows
    public void xml() {
        Person person = mapper.readValue(new File("src/main/java/entity/example.xml"), Person.class);
        System.out.println(person);
        Assert.assertEquals("City1", person.getAddress().get(0).getCity());

        String s = mapper.writeValueAsString(person);
        System.out.println(s);
    }

}
