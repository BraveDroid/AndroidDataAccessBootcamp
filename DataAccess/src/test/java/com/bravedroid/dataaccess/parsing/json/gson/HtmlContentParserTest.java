package com.bravedroid.dataaccess.parsing.json.gson;

import com.bravedroid.dataaccess.model.HtmlContent;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HtmlContentParserTest {
    private HtmlContentParser SUT;

    @Before
    public void setUp() {
        SUT = new HtmlContentParser();
    }

    @Test
    public void parseHtmlContentTest() {
        HtmlContent htmlContent = new HtmlContent("<html><body><h1>futurestud.io</h1></body></html>");
        String result = SUT.parseHtmlContent(htmlContent);
        String expected = "{\"htmlContent\":\"<html><body><h1>futurestud.io</h1></body></html>\"}";
        assertThat(result, is(equalTo(expected)));
    }
}
