package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserDayEnum;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EnumsMappingTest {
    public EnumsMapping SUT;

    @Before
    public void setUp() {
        SUT = new EnumsMapping();
    }

    @Test
    public void serializeEnumsTest() {
        UserDayEnum userDayEnum = new UserDayEnum("Norman", "norman@futurestud.io", true, 26, UserDayEnum.Day.SUNDAY);
        String result = SUT.serializeEnums(userDayEnum);
        String expected = "{\"_name\":\"Norman\",\"email\":\"norman@futurestud.io\",\"isDeveloper\":true,\"age\":26,\"day\":\"SUNDAY\"}";
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeEnumsTest() {
        String input = "{\"_name\":\"Norman\",\"email\":\"norman@futurestud.io\",\"isDeveloper\":true,\"age\":26,\"day\":\"SUNDAY\"}";
        UserDayEnum expected = new UserDayEnum("Norman", "norman@futurestud.io", true, 26, UserDayEnum.Day.SUNDAY);
        UserDayEnum result = SUT.deserializeEnums(input);
        assertThat(result, is(equalTo(expected)));
    }
}
