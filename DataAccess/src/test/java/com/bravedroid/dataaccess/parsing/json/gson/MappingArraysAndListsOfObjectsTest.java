package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.Book;
import com.bravedroid.dataaccess.model.Library;
import com.bravedroid.dataaccess.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MappingArraysAndListsOfObjectsTest {
    private MappingArraysAndListsOfObjects SUT;


    @Before
    public void setUp() {
        SUT = new MappingArraysAndListsOfObjects();
    }

    @Test
    public void mapListSerializationTest() {
        String result = SUT.mapListSerialisation();
        String expected = "{\"libraryAddress\":\" 50 place republic \"," + "\"listOfBooks\":" +
                "[" +
                "{\"title\":\"User story Mapping\"" +
                ",\"author\":\"Jeff Patten\"," +
                "\"price\":35.0" +
                ",\"user\":" +
                "{\"age\":20," +
                "\"firstName\":\"sam\"" +
                ",\"lastName\":\"spenser\"}" +
                "}" +
                ",{\"title\":\"Streaming Systems\"," +
                "\"author\":\"Tyler Akidau, Slava Chernyak, Reuven Lax\"," +
                "\"price\":35.0" +
                ",\"user\":" +
                "{\"age\":29," +
                "\"firstName\":\"guess\"," +
                "\"lastName\":\"white\"}" +
                "}" +
                ",{\"title\":\"Deep Learning Cookbook\"," +
                "\"author\":\"Douwe Osinga\"," +
                "\"price\":35.0," +
                "\"user\":" +
                "{\"age\":28," +
                "\"firstName\":\"shon\"," +
                "\"lastName\":\"naser\"}" +
                "}," +
                "{\"title\":\"Jenkins 2: Up and Running\"," +
                "\"author\":\"Brent Laster\"," +
                "\"price\":35.0," +
                "\"user\":" +
                "{\"age\":33," +
                "\"firstName\":\"july\"," +
                "\"lastName\":\"lasiter\"}" +
                "}" +
                "]" +
                "}";
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void mapListDeserializationTest() throws IOException {
        InputStream libraryDataAsStream = Objects.requireNonNull(getClass().getClassLoader()).getResourceAsStream("library_data.json");
        User user1 = new User(20, "sam", "spenser");
        User user2 = new User(29, "guess", "white");
        User user3 = new User(28, "shon", "naser");
        User user4 = new User(33, "july", "lasiter");

        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(new Book("User story Mapping", "Jeff Patten", 35, user1));
        listOfBooks.add(new Book("Streaming Systems", "Tyler Akidau, Slava Chernyak, Reuven Lax", 35, user2));
        listOfBooks.add(new Book("Deep Learning Cookbook", "Douwe Osinga", 35, user3));
        listOfBooks.add(new Book("Jenkins 2: Up and Running", "Brent Laster", 35, user4));

        Library result = SUT.mapListDeserialization(libraryDataAsStream);
        Library expected = new Library(" 50 place republic ", listOfBooks);
        assertThat(result, is(equalTo(expected)));
    }
}
