package com.bravedroid.dataaccess.fetching.network.http.http_url_connection;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class GitHubRequestHelperTest {
    private GitHubRequestHelper SUT;

    @Before
    public void setUp() {
        SUT = new GitHubRequestHelper();
    }

    @Test
    public void getBaseUrlTest() {
        InputStream result = SUT.getBaseUrl();
        assertNotNull(result);
    }
}
