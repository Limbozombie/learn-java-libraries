package usage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import entity.Book;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class JDK8Test {
    ObjectMapper mapper;

    @Before
    public void init() {
        mapper = new JsonMapper();

        mapper.registerModule(new Jdk8Module());
    }

    @Test
    @SneakyThrows
    public void usage() {
        Book book = new Book();
        book.setTitle("Oliver Twist");
        book.setSubTitle(Optional.of("The Parish Boy's Progress"));

        String serializedBook = mapper.writeValueAsString(book);

        System.out.println(serializedBook);

        String bookJson = "{ \"title\": \"Oliver Twist\", \"subTitle\": \"foo\" }";
        Book result = mapper.readValue(bookJson, Book.class);
        System.out.println(result);
    }


}
