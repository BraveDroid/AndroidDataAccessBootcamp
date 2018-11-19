package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserDate;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ParsingWithExclusionStrategiesTest {
    private ParsingWithExclusionStrategies SUT;

    @Before
    public void setUp() {
        SUT = new ParsingWithExclusionStrategies();
    }

    @Test
    public void serializeWithExclusionStrategiesTest() {
        UserDate inputDto = new UserDate("Norman", "norman@futurestud.io", true, 26, new Date());
        String expected = "{\"email\":\"norman@futurestud.io\"}";
        String result = SUT.serializeWithExclusionStrategies(inputDto);
        assertEquals(expected, result);
    }

    @Test
    public void deserializeWithExclusionStrategiesTest() {
        String input = "{\"email\":\"norman@futurestud.io\"}";
        UserDate result = SUT.deserializeWithExclusionStrategies(input);
        UserDate expected = new UserDate(null, "norman@futurestud.io", true, 0, null);
        assertThat(result, is(isCustomEqual(expected)));
    }

    private Matcher<UserDate> isCustomEqual(final UserDate userDate) {
        return new BaseMatcher<UserDate>() {
            @Override
            public boolean matches(Object obj) {
                if (!(obj instanceof UserDate)) {
                    return false;
                }
                if (isBothNameNull((UserDate) obj, userDate) && (isBothRegisterDateNull((UserDate) obj, userDate))) {
                    return userDate.email.equals(((UserDate) obj).email)
                            && userDate.age == 0 && ((UserDate) obj).age == 0
                            && userDate.isDeveloper == ((UserDate) obj).isDeveloper;
                }
                if (isBothNameNotNull((UserDate) obj, userDate) && (isBothRegisterDateNotNull((UserDate) obj, userDate))) {
                    return userDate.email.equals(((UserDate) obj).email)
                            && userDate.age == 0 && ((UserDate) obj).age == 0
                            && userDate.isDeveloper == ((UserDate) obj).isDeveloper;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("they should be equals ").appendValue(userDate);
            }
        };
    }

    private boolean isBothRegisterDateNotNull(UserDate obj, UserDate userDate) {
        return userDate.registerDate != null && obj.registerDate != null;
    }

    private boolean isBothNameNotNull(UserDate obj, UserDate userDate) {
        return userDate._name != null && obj._name != null;
    }

    private boolean isBothRegisterDateNull(UserDate obj, UserDate userDate) {
        return userDate.registerDate == null && obj.registerDate == null;
    }

    private boolean isBothNameNull(UserDate obj, UserDate userDate) {
        return userDate._name == null && obj._name == null;
    }
}
