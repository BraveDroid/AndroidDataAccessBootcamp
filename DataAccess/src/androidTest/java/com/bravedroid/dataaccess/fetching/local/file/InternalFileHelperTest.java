package com.bravedroid.dataaccess.fetching.local.file;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InternalFileHelperTest {
    private static InternalFileHelper SUT;
    private static Context appContext;

    @BeforeClass
    public static void setUp() {
        appContext = InstrumentationRegistry.getTargetContext();
        SUT = new InternalFileHelper();
    }

    @After
    public void tearDown() {
        SUT.deleteAllInternalFiles(appContext);
        SUT.deleteAllInternalCacheFiles(appContext);
    }

    @Test
    public void createFileTest() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateFile(appContext, fileName, fileBody);
        String result = SUT.readFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody)));
    }

    @Test
    public void createCacheFileTest() {
        String fileBody = "Hello Every body fom my cache file :)!!";
        String fileName = "myCacheFile";
        SUT.createOrUpdateCacheFile(appContext, fileName, fileBody);
        String result = SUT.readCacheFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody)));
    }

    @Test
    public void updateFileTest() {
        String fileName = "myFile";
        String fileBody = "Hello Every body :)!!";
        SUT.createOrUpdateFile(appContext, fileName, fileBody);

        SUT.createOrUpdateFile(appContext, fileName, "good morning from paris", true);
        String result = SUT.readFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody + "good morning from paris")));
    }

    @Test
    public void updateCacheFileTest() {
        String fileName = "myFile";
        String fileBody = "Hello Every body fom my cache file :)!!";
        SUT.createOrUpdateCacheFile(appContext, fileName, fileBody);

        SUT.createOrUpdateCacheFile(appContext, fileName, "good morning from paris", true);
        String result = SUT.readCacheFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody + "good morning from paris")));
    }

    @Test
    public void listFileReadTest() {
        createSampleFile();
        String[] result = SUT.listNameFiles(appContext);
        String[] expected = {"myFile"};
        assertThat(result, is(equalTo(expected)));
    }

    private void createSampleFile() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateFile(appContext, fileName, fileBody);
    }

    @Test
    public void listCacheFileReadTest() {
        createSampleCacheFile();
        String[] result = SUT.listNameCacheFiles(appContext);
        String[] expected = {"myFile"};
        assertThat(result, is(equalTo(expected)));
    }

    private void createSampleCacheFile() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateCacheFile(appContext, fileName, fileBody);
    }

    @Test
    public void deleteTest() {
        String fileName1 = "myFirstFile";
        String fileBody1 = "Hello Every body :)!!";
        SUT.createOrUpdateFile(appContext, fileName1, fileBody1);

        String fileName2 = "mySecondFile";
        String fileBody2 = "How are you:)!!";
        SUT.createOrUpdateFile(appContext, fileName2, fileBody2);

        String fileName3 = "myThirdFile";
        String fileBody3 = "thanks :)!!";
        SUT.createOrUpdateFile(appContext, fileName3, fileBody3);

        boolean result = SUT.deleteFile(appContext, fileName2);
        Assert.assertTrue(result);

        String[] listNameFiles = SUT.listNameFiles(appContext);
        String[] expectedListNameFiles = {"myFirstFile", "myThirdFile"};
        assertThat(listNameFiles, is(equalTo(expectedListNameFiles)));
    }

    @Test
    public void deleteCacheTest() {
        String fileName1 = "myFirstCacheFile";
        String fileBody1 = "Hello Every body :)!!";
        SUT.createOrUpdateCacheFile(appContext, fileName1, fileBody1);

        String fileName2 = "mySecondCacheFile";
        String fileBody2 = "How are you:)!!";
        SUT.createOrUpdateCacheFile(appContext, fileName2, fileBody2);

        String fileName3 = "myThirdCacheFile";
        String fileBody3 = "thanks :)!!";
        SUT.createOrUpdateCacheFile(appContext, fileName3, fileBody3);

        boolean result = SUT.deleteCacheFile(appContext, fileName2);
        Assert.assertTrue(result);

        String[] listNameFiles = SUT.listNameCacheFiles(appContext);
        String[] expectedListNameFiles = {"myFirstCacheFile", "myThirdCacheFile"};
        assertThat(listNameFiles, is(equalTo(expectedListNameFiles)));
    }

    @Test
    public void deleteAllFilesTest() {
        createSampleFile();
        String[] listNameFiles = SUT.listNameFiles(appContext);
        int result = listNameFiles.length;
        assertThat(result, is(equalTo(1)));

        SUT.deleteAllInternalFiles(appContext);
        listNameFiles = SUT.listNameFiles(appContext);
        result = listNameFiles.length;
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void deleteAllCacheFilesTest() {
        createSampleCacheFile();
        String[] listNameCacheFiles = SUT.listNameCacheFiles(appContext);
        int result = listNameCacheFiles.length;
        assertThat(result, is(equalTo(1)));

        SUT.deleteAllInternalCacheFiles(appContext);
        listNameCacheFiles = SUT.listNameCacheFiles(appContext);
        result = listNameCacheFiles.length;
        assertThat(result, is(equalTo(0)));
    }
}
