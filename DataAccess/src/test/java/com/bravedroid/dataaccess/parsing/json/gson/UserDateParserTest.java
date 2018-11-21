package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserDate1;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDateParserTest {
    private UserDateParser SUT;

    @Before
    public void setUp() {
        SUT = new UserDateParser();
    }

    @Test
    public void serializeUserDate() {
        UserDate1 user = new UserDate1("Norman", "norman@futurestud.io", true, 26, getDate("12:08 PM", "h:mm a"));
        String result = SUT.serializeUserDate(user);
        String expected = "{\"_name\":\"Norman\",\"email\":\"norman@futurestud.io\",\"isDeveloper\":true,\"age\":26,\"registerDate\":\"12:08 PM\"}";
        assertThat(result, is(equalTo(expected)));
    }

    private Date getDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
