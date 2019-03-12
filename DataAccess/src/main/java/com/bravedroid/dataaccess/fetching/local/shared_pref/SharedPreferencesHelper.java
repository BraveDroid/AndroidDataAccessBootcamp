package com.bravedroid.dataaccess.fetching.local.shared_pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

public class SharedPreferencesHelper {
    /**
     * default sharedPreferences Instance, application level
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * sharedPreferences Instance, application level
     *
     * @param context
     * @param fileName
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context, String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * sharedPreferences Instance, activity level
     *
     * @param activity
     * @return
     */
    public static SharedPreferences getSharedPreferences(Activity activity) {
        return activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void save(SharedPreferences sharedPreferences, @NonNull Callback callback) {
        SharedPreferences.Editor edit = callback.onEditorCreation(sharedPreferences.edit());
        if (edit != null) edit.commit();
    }

    public void saveAsync(SharedPreferences sharedPreferences, @NonNull Callback callback) {
        SharedPreferences.Editor edit = callback.onEditorCreation(sharedPreferences.edit());
        if (edit != null) edit.apply();
    }

    interface Callback {
        SharedPreferences.Editor onEditorCreation(SharedPreferences.Editor edit);
    }
}
