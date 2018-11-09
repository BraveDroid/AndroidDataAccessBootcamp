package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleParsingTest {
    private SimpleParsing SUT;

    @Before
    public void setUp() {
        SUT = new SimpleParsing();
    }

    @Test
    public void toJsonStringTest() {
        User user = new User(50, "Mike", "Tomas");
        String userString = SUT.toJsonString(user);
        String expected = "{\"age\":50,\"firstName\":\"Mike\",\"lastName\":\"Tomas\"}";
        assertEquals(expected, userString);
    }

    @Test
    public void toObjectTest() {
        String userString = "{\"age\":50,\"firstName\":\"Mike\",\"lastName\":\"Tomas\"}";
        User expectedUser = new User(50, "Mike", "Tomas");
        User user = SUT.toObject(userString);
        assertEquals(expectedUser, user);
    }
}