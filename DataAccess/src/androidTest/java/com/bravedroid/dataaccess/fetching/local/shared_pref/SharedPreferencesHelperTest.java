package com.bravedroid.dataaccess.fetching.local.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SharedPreferencesHelperTest {
    private SharedPreferencesHelper SUT;
    private Context appContext;

    @Before
    public void setUp() {
        SUT = new SharedPreferencesHelper();
        appContext = InstrumentationRegistry.getTargetContext();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void saveTest() {
        SUT.save(SharedPreferencesHelper.getSharedPreferences(appContext), new SharedPreferencesHelper.Callback() {

            @Override
            public SharedPreferences.Editor onEditorCreation(SharedPreferences.Editor edit) {
                return edit.putBoolean("firstKey", true);
            }
        });
        boolean result = SharedPreferencesHelper.getSharedPreferences(appContext).getBoolean("firstKey", false);
        Assert.assertTrue(result);
    }
}
