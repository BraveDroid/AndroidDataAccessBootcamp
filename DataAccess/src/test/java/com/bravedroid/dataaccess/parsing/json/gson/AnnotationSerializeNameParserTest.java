package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.BookModel2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnnotationSerializeNameParserTest {
    private AnnotationSerializeNameParser SUT;

    @Before
    public void setUp() {
        SUT = new AnnotationSerializeNameParser();
    }

    @Test
    public void serializeBookModel2Test() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("bookModel2.json");
        String expected = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());

        String result = SUT.serializeBookModel2();
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeBookModelTest() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("bookModel2.json");
        String input = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        BookModel2 expected = new BookModel2("Gson", "Norman Peitek", 30);
        BookModel2 result = SUT.deserializeBookModel2(input);
        assertThat(result, is(equalTo(expected)));
    }
}
