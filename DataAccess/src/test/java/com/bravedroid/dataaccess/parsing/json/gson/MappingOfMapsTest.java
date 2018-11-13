package com.bravedroid.dataaccess.parsing.json.gson;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MappingOfMapsTest {
    private MappingOfMaps SUT;

    @Before
    public void setUp() {
        SUT = new MappingOfMaps();
    }

    @Test
    public void serializeMapTest() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("employees.json");
        String expected = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        String result = SUT.serializeMap();
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeMapTest() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("employees.json");
        String employeesJsonString = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());

        HashMap<String, List<String>> expected = new HashMap<>();
        expected.put("A", Arrays.asList("Andreas", "Arnold", "Aden"));
        expected.put("C", Arrays.asList("Christian", "Carter"));
        expected.put("M", Arrays.asList("Marcus", "Mary"));

        HashMap<String, List<String>> result = SUT.deserializeMap(employeesJsonString);
        assertEquals(expected, result);
    }
}
