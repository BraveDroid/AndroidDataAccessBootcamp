package com.bravedroid.dataaccess.fetching.local.read_only;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class ReadOnlyFileHelperTest {
    private ReadOnlyFileHelper SUT;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        SUT = new ReadOnlyFileHelper(appContext);
    }

    @Test
    public void getInputStreamFromRaw_shouldNot_return_null() {
        assertNotNull(SUT.getInputStreamFromRaw());
    }

    @Test
    public void getInputStreamFromRawAsync_shouldNot_return_null() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final InputStream[] resultStream = new InputStream[1];

        SUT.getInputStreamFromRawAsync(new ReadOnlyFileHelper.Callback() {
            @Override
            public void onFileOpen(InputStream stream) {
                resultStream[0] = stream;
                latch.countDown();
            }

        });

        latch.await(2, TimeUnit.SECONDS);
        assertNotNull(resultStream[0]);
    }

    @Test
    public void getInputStreamFromAssets_shouldNot_return_null() {
        assertNotNull(SUT.getInputStreamFromAssets());
    }

    @Test
    public void getInputStreamFromAssetAsync_shouldNot_return_null() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final InputStream[] resultStream = new InputStream[1];

        SUT.getInputStreamFromAssetsAsync(new ReadOnlyFileHelper.Callback() {
            @Override
            public void onFileOpen(InputStream stream) {
                resultStream[0] = stream;
                latch.countDown();
            }

            @Override
            public void onFileNotFoundError() {
                fail("the file is not found ");
            }
        });
        latch.await(2, TimeUnit.SECONDS);
        assertNotNull(resultStream[0]);
    }
}
