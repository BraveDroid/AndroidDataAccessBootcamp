package com.bravedroid.dataaccess.parsing.json.gson;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValuesOfFloatsSerializerTest {
    private ValuesOfFloatsSerializer SUT;

    @Before
    public void setUp() {
        SUT = new ValuesOfFloatsSerializer();
    }

    @Test
    public void serializeValuesOfFloatsTest() {
        String result = SUT.serializeValuesOfFloats();
        String expected = "{\"name\":\"Norman\",\"weight\":Infinity}";
        assertThat(result, is(equalTo(expected)));
    }
}
