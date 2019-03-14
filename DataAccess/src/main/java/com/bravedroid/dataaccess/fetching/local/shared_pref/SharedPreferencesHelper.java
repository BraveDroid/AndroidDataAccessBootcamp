package com.bravedroid.dataaccess.fetching.local.shared_pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

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

    public static <T> T get(SharedPreferences sharedPreferences, String key, T defaultValue) {
        if (defaultValue instanceof String)
            return (T) sharedPreferences.getString(key, (String) defaultValue);
        if (defaultValue instanceof Boolean)
            return (T) new Boolean(sharedPreferences.getBoolean(key, (Boolean) defaultValue));
        if (defaultValue instanceof Float)
            return (T) new Float(sharedPreferences.getFloat(key, (Float) defaultValue));
        if (defaultValue instanceof Integer)
            return (T) new Integer(sharedPreferences.getInt(key, (Integer) defaultValue));
        if (defaultValue instanceof Long)
            return (T) new Long(sharedPreferences.getLong(key, (Long) defaultValue));
        if (defaultValue instanceof Set<?>) {
            Set<?> set = (Set<?>) defaultValue;
            Set<String> setResult = new HashSet<>();
            for (Object element : set) {
                if (element instanceof String) {
                    setResult.add((String) element);
                }
            }
            return (T) sharedPreferences.getStringSet(key, setResult);
        }
        throw new IllegalArgumentException();
    }

    interface Callback {
        SharedPreferences.Editor onEditorCreation(SharedPreferences.Editor edit);
    }
}
