package com.bravedroid.dataaccess.parsing.properties;

import com.bravedroid.dataaccess.model.Config;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SampleConfigPropertiesMappingTest {
    private SampleConfigPropertiesMapping SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new SampleConfigPropertiesMapping();
    }

    @Test
    public void getConfig() {
        InputStream configPropertiesAsStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        Config config = SUT.getConfig(configPropertiesAsStream);
        Config expected = new Config("google.com", 80, false);
        assertThat(config, is(equalTo(expected)));
    }
}
