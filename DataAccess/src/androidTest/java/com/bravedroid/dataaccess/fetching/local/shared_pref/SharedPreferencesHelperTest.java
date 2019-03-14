package com.bravedroid.dataaccess.fetching.local.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
        SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(appContext);
        sharedPreferences.edit().clear();
    }

    @Test
    public void saveTest() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(appContext);
        SUT.save(sharedPreferences, new SharedPreferencesHelper.Callback() {

            @Override
            public SharedPreferences.Editor onEditorCreation(SharedPreferences.Editor edit) {
                return edit.putBoolean("firstKey", true);
            }
        });
        boolean result = SharedPreferencesHelper.get(sharedPreferences, "firstKey", true);
        Assert.assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTestShouldThrowExceptionIfNonPrimitiveTypeIsUsedAsDefaultValueArg() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(appContext);
        String[] keys = SharedPreferencesHelper.get(sharedPreferences, "Key", new String[]{"item1", "item2"});
    }

    @Test
    public void getTestShouldThrowExceptionIfNonStringSetTypeIsUsedAsDefaultValueArg() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.getSharedPreferences(appContext);
        Set set2 = new HashSet<Integer>();
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        Set set = SharedPreferencesHelper.get(sharedPreferences, "Key", set2);
        System.out.println(set);
        Assert.assertThat(set, CoreMatchers.<Set>is(new HashSet<Integer>()));
    }
}
