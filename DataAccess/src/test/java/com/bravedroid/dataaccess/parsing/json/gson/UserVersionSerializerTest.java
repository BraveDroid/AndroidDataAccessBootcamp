package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserVersion;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserVersionSerializerTest {
    private UserVersionSerializer SUT;

    @Before
    public void setUp() {
        SUT = new UserVersionSerializer();
    }

    @Test
    public void serializeUserVersionTest() {
        UserVersion userVersion1 = new UserVersion("Norman", "norman@futurestud.io", true, 26);
        String result = SUT.serializeUserVersion(userVersion1);
        String expected = "{\"name\":\"Norman\",\"email\":\"norman@futurestud.io\"}";
        assertThat(result, is(equalTo(expected)));
    }
}
