package com.bravedroid.dataaccess.parsing.base64;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class Base64HelperTest {
    private Base64Helper SUT;

    @Before
    public void setUp() {
        SUT = new Base64Helper();
    }

    @Test
    public void enCodeToBase64Test() {
        String input = "A compile time error will be generated";
        String result = SUT.enCodeToBase64(input);
        String expected = "QSBjb21waWxlIHRpbWUgZXJyb3Igd2lsbCBiZSBnZW5lcmF0ZWQ=";
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void decodeFromBase64Test() {
        String input = "QSBjb21waWxlIHRpbWUgZXJyb3Igd2lsbCBiZSBnZW5lcmF0ZWQ=";
        String result = SUT.decodeFromBase64(input);
        String expected = "A compile time error will be generated";
        assertThat(result, is(equalTo(expected)));
    }
}
