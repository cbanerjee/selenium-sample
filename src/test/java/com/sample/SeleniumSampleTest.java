package com.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import com.sample.SeleniumSample;

public class SeleniumSampleTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAppConstructor() {
        try {
            new SeleniumSample();
        } catch (Exception e) {
            fail("Construction failed.");
        }
    }

    @Test
    public void testAppMain()
    {
    	SeleniumSample.main(null);
        try {
            assertEquals("Hello, The app is working" + System.getProperty("line.separator"), outContent.toString());
        } catch (AssertionError e) {
            fail("\"message\" is not \"Hello, The app is working\"");
        }
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}
