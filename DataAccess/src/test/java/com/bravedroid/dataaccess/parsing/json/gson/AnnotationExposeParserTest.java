package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.BookModel;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
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

public class AnnotationExposeParserTest {
    private AnnotationExposeParser SUT;

    @Before
    public void setUp() {
        SUT = new AnnotationExposeParser();
    }

    @Test
    public void serializeBookModel() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("bookModel.json");
        String expected = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        String result = SUT.serializeBookModel();
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeBookModel() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("bookModel.json");
        String input = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        BookModel result = SUT.deserializeBookModel(input);
        BookModel expected = new BookModel(null, "jeff patton", 35, 0);

        assertThat(result, isCustomEqual(expected));
    }

    private Matcher<BookModel> isCustomEqual(final BookModel book) {
        return new BaseMatcher<BookModel>() {
            @Override
            public boolean matches(final Object obj) {
                if (!(obj instanceof BookModel)) {
                    return false;
                }
                if (book.title == null && ((BookModel) obj).title == null) {
                    return book.author.equals(((BookModel) obj).author)
                            && book.price == ((BookModel) obj).price
                            && book.hoursOfWork == ((BookModel) obj).hoursOfWork;
                } else if (book.title != null && ((BookModel) obj).title != null) {
                    return book.author.equals(((BookModel) obj).author)
                            && book.price == ((BookModel) obj).price
                            && book.title.equals(((BookModel) obj).title)
                            && book.hoursOfWork == ((BookModel) obj).hoursOfWork;
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("they should be equals ").appendValue(book);
            }
        };
    }
}
