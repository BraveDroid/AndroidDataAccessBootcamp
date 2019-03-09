package com.bravedroid.dataaccess.fetching.local.file;

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.GrantPermissionRule;
import org.junit.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExternalFileHelperTest {
    private static ExternalFileHelper SUT;
    private static Context appContext;
    @Rule
    public GrantPermissionRule writePermissionRule = GrantPermissionRule.grant(
            Manifest.permission.WRITE_EXTERNAL_STORAGE);
    @Rule
    public GrantPermissionRule readPermissionRule = GrantPermissionRule.grant(
            Manifest.permission.READ_EXTERNAL_STORAGE);

    @BeforeClass
    public static void setUp() {
        appContext = InstrumentationRegistry.getTargetContext();
        SUT = new ExternalFileHelper();
    }

    @After
    public void tearDown() {
        SUT.deleteAllExternalFiles(appContext);
        SUT.deleteAllPublicExternalFiles();
        SUT.deleteAllExternalCacheFiles(appContext);
    }

    @Test
    public void createOrUpdateExternalFile() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateExternalFile(appContext, fileName, fileBody);
        String result = SUT.readExternalFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody)));
    }

    @Test
    public void createOrUpdateExternalCacheFile() {
        String fileBody = "Hello Every body fom my cache file :)!!";
        String fileName = "myCacheFile";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName, fileBody);
        String result = SUT.readCacheExternalFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody)));
    }

    @Test
    public void updateFileTest() {
        String fileName = "myFile";
        String fileBody = "Hello Every body :)!!";
        SUT.createOrUpdateExternalFile(appContext, fileName, fileBody);

        SUT.createOrUpdateExternalFile(appContext, fileName, "good morning from paris", true);
        String result = SUT.readExternalFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody + "good morning from paris")));
    }

    @Test
    public void updateCacheFileTest() {
        String fileName = "myFile";
        String fileBody = "Hello Every body fom my cache file :)!!";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName, fileBody);

        SUT.createOrUpdateExternalCacheFile(appContext, fileName, "good morning from paris", true);
        String result = SUT.readCacheExternalFileContent(appContext, fileName);
        assertThat(result, is(equalTo(fileBody + "good morning from paris")));
    }

    @Test
    public void listNameExternalFilesTest() {
        createSampleFile();
        String[] result = SUT.listNameExternalFiles(appContext);
        String[] expected = {"myFile"};
        assertThat(result, is(equalTo(expected)));
    }

    private void createSampleFile() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateExternalFile(appContext, fileName, fileBody);
    }

    @Test
    public void listNameExternalCacheFilesTest() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName, fileBody);

        String[] result = SUT.listNameExternalCacheFiles(appContext);
        String[] expected = {"myFile"};
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void listNamePublicExternalFilesTest() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdatePublicExternalFile(fileName, fileBody);

        String[] result = SUT.listNamePublicExternalFiles();
        String[] expected = {"myFile"};
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void deleteExternalFile() {
        String fileName1 = "myFirstCacheFile";
        String fileBody1 = "Hello Every body :)!!";
        SUT.createOrUpdateExternalFile(appContext, fileName1, fileBody1);

        String fileName2 = "mySecondCacheFile";
        String fileBody2 = "How are you:)!!";
        SUT.createOrUpdateExternalFile(appContext, fileName2, fileBody2);

        String fileName3 = "myThirdCacheFile";
        String fileBody3 = "thanks :)!!";
        SUT.createOrUpdateExternalFile(appContext, fileName3, fileBody3);

        boolean result = SUT.deleteExternalFile(appContext, fileName2);
        Assert.assertTrue(result);

        String[] listNameFiles = SUT.listNameExternalFiles(appContext);
        String[] expectedListNameFiles = {"myFirstCacheFile", "myThirdCacheFile"};
        assertThat(listNameFiles, is(equalTo(expectedListNameFiles)));
    }

    @Test
    public void deleteExternalCacheFile() {
        String fileName1 = "myFirstCacheFile";
        String fileBody1 = "Hello Every body :)!!";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName1, fileBody1);

        String fileName2 = "mySecondCacheFile";
        String fileBody2 = "How are you:)!!";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName2, fileBody2);

        String fileName3 = "myThirdCacheFile";
        String fileBody3 = "thanks :)!!";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName3, fileBody3);

        boolean result = SUT.deleteExternalCacheFile(appContext, fileName2);
        Assert.assertTrue(result);

        String[] externalCacheFiles = SUT.listNameExternalCacheFiles(appContext);
        String[] expectedListExternalCacheNameFiles = {"myFirstCacheFile", "myThirdCacheFile"};
        assertThat(externalCacheFiles, is(equalTo(expectedListExternalCacheNameFiles)));
    }

    @Test
    public void deleteAllExternalFiles() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateExternalFile(appContext, fileName, fileBody);

        String[] listNameFiles = SUT.listNameExternalFiles(appContext);
        int result = listNameFiles.length;
        assertThat(result, is(equalTo(1)));

        SUT.deleteAllExternalFiles(appContext);
        listNameFiles = SUT.listNameExternalFiles(appContext);
        result = listNameFiles.length;
        assertThat(result, is(equalTo(0)));
    }

    @Test
    public void deleteAllExternalCacheFiles() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdateExternalCacheFile(appContext, fileName, fileBody);
        String[] listNameCacheFiles = SUT.listNameExternalCacheFiles(appContext);
        int result = listNameCacheFiles.length;
        assertThat(result, is(equalTo(1)));

        SUT.deleteAllExternalCacheFiles(appContext);
        listNameCacheFiles = SUT.listNameExternalCacheFiles(appContext);
        result = listNameCacheFiles.length;
        assertThat(result, is(equalTo(0)));
    }

    private void createSamplePublicFile() {
        String fileBody = "Hello Every body :)!!";
        String fileName = "myFile";
        SUT.createOrUpdatePublicExternalFile(fileName, fileBody);
    }

    @Test
    public void deleteAllPublicExternalFiles() {
        createSamplePublicFile();
        String[] listNameFiles = SUT.listNamePublicExternalFiles();
        int result = listNameFiles.length;
        assertThat(result, is(equalTo(1)));

        SUT.deleteAllPublicExternalFiles();
        listNameFiles = SUT.listNamePublicExternalFiles();
        result = listNameFiles.length;
        assertThat(result, is(equalTo(0)));
    }
}
