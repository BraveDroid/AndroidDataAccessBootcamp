package com.bravedroid.dataaccess.parsing.json.gson;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MappingOfSetsTest {
    private MappingOfSets SUT;

    @Before
    public void setUp() {
        SUT = new MappingOfSets();
    }

    @Test
    public void serialiseUserHashSet() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("hashSetUsers.json");
        String expected = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        String result = SUT.serialiseHashSet();
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deserializeUserHashSet() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("hashSetUsers.json");
        String inputsString = new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8.name());
        Set<String> result = SUT.deserializeHashSet(inputsString);
        Set<String> expected = new HashSet<>();
        expected.add("Christian");
        expected.add("Marcus");
        expected.add("Norman");
        assertThat(result, is(equalTo(expected)));
    }
}
