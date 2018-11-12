package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.Book;
import com.bravedroid.dataaccess.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicParsingWithGsonTest {
    private BasicParsingWithGson SUT;

    @Before
    public void setUp() {
        SUT = new BasicParsingWithGson();
    }

    @Test
    public void serializeBaseModelTest() {
        User user = new User(30, "matata", "hakouna");
        Book book = new Book("GSON", "Mark", 30, user);
        String resutlt = SUT.serializeBaseModel(book);
        String expected = "{\"author\":\"Mark\",\"price\":30.0,\"title\":\"GSON\",\"user\":{\"age\":30,\"firstName\":\"matata\",\"lastName\":\"hakouna\"}}";
        assertEquals(expected, resutlt);
    }

    @Test
    public void deserializeBaseModelTest() {
        User user = new User(30, "matata", "hakouna");
        Book book = SUT.deserializeBaseModel("{\"author\":\"Mark\",\"price\":30.0,\"title\":\"GSON\",\"user\":{\"age\":30,\"firstName\":\"matata\",\"lastName\":\"hakouna\"}}");
        Book expected = new Book("GSON", "Mark", 30, user);
        assertEquals(expected, book);
    }
}
