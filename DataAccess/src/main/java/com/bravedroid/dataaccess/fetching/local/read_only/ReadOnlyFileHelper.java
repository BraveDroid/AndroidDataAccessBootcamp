package com.bravedroid.dataaccess.fetching.local.read_only;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.bravedroid.dataaccess.R;

import java.io.IOException;
import java.io.InputStream;

public class ReadOnlyFileHelper {
    private final Context appContext;

    public ReadOnlyFileHelper(Context appContext) {
        this.appContext = appContext;
    }

    public InputStream getInputStreamFromRaw() {
        return appContext.getResources().openRawResource(R.raw.project_config);
    }

    public void getInputStreamFromRawAsync(@NonNull final Callback callback) {
        new BackgroundTask(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = appContext.getResources().openRawResource(R.raw.project_config);

                callback.onFileOpen(inputStream);

            }
        }).execute();
    }

    public InputStream getInputStreamFromAssets() {
        InputStream open = null;
        AssetManager assets = appContext.getAssets();
        try {
            open = assets.open("config/project_config.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return open;
    }

    public void getInputStreamFromAssetsAsync(@NonNull final Callback callback) {
        new BackgroundTask(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = appContext.getAssets().open("config/project_config.json");
                    callback.onFileOpen(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFileNotFoundError();
                }
            }
        }).execute();
    }


    public static abstract class Callback {
        public abstract void onFileOpen(InputStream stream);

        public void onFileNotFoundError() {
        }
    }

    private static class BackgroundTask extends AsyncTask<Void, Void, Void> {
        private final Runnable backgroundInstructions;

        public BackgroundTask(Runnable backgroundInstructions) {
            this.backgroundInstructions = backgroundInstructions;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            backgroundInstructions.run();
            return null;
        }
    }
}
