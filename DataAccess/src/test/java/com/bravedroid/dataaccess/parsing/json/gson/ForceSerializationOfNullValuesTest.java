package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.SimpleUser;
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

public class ForceSerializationOfNullValuesTest {
    private static final String SIMPLE_USER_JSON = "simpleUser.json";
    private ForceSerializationOfNullValues SUT;

    @Before
    public void setUp() {
        SUT = new ForceSerializationOfNullValues();
    }

    @Test
    public void serializeNullValuesTest() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("simpleUser.json");
        String expected = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        String result = SUT.serializeNullValues();
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeNullValuesTest() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource(SIMPLE_USER_JSON);
        String input = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        SimpleUser expected = new SimpleUser("Norman", null, true, 26);
        SimpleUser result = SUT.deserializeNullValues(input);
        assertThat(result, is(isCustomEqual(expected)));
    }

    private Matcher<SimpleUser> isCustomEqual(final SimpleUser simpleUser) {
        return new BaseMatcher<SimpleUser>() {
            @Override
            public boolean matches(Object obj) {
                if (!(obj instanceof SimpleUser)) {
                    return false;
                }
                if (simpleUser.email == null && ((SimpleUser) obj).email == null) {
                    return simpleUser.name.equals(((SimpleUser) obj).name)
                            && simpleUser.isDeveloper == ((SimpleUser) obj).isDeveloper
                            && simpleUser.age == ((SimpleUser) obj).age;
                } else if (simpleUser.email != null && ((SimpleUser) obj).email != null) {
                    return simpleUser.email.equals(((SimpleUser) obj).email)
                            && simpleUser.name.equals(((SimpleUser) obj).name)
                            && simpleUser.isDeveloper == ((SimpleUser) obj).isDeveloper
                            && simpleUser.age == ((SimpleUser) obj).age;
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("they should be equals ").appendValue(simpleUser);
            }
        };
    }
}
