package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.UserFloat;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class ValuesOfFloatsSerializerTest {
    private ValuesOfFloatsSerializer SUT;

    @Before
    public void setUp() {
        SUT = new ValuesOfFloatsSerializer();
    }

    @Test
    public void serializeValuesOfFloatsTest() {
        UserFloat userFloatInput = new UserFloat("Norman", Float.POSITIVE_INFINITY);
        String result = SUT.serializeValuesOfFloats(userFloatInput);
        String expected = "{\"name\":\"Norman\",\"weight\":Infinity}";
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeValuesOfFloatsTest() {
        String userStringJson1 = "{'name':'Norman', 'weight':Infinity}";
        UserFloat userFloat1 = SUT.deserializeValuesOfFloats(userStringJson1);
        assertThat(userFloat1.name, is(equalTo("Norman")));
        assertThat(userFloat1.weight, is(equalTo(Float.POSITIVE_INFINITY)));

        String userStringJson2 = "{'name':'Norman', 'weight':'10.99'}";
        UserFloat userFloat2 = SUT.deserializeValuesOfFloats(userStringJson2);
        assertThat(userFloat2.name, is(equalTo("Norman")));
        assertThat(userFloat2.weight.doubleValue(), is(closeTo(10.99, 0.01)));
    }
}
