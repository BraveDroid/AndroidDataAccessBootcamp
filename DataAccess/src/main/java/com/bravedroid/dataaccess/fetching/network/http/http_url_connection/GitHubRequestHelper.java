package com.bravedroid.dataaccess.fetching.network.http.http_url_connection;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GitHubRequestHelper {
    public static final String BASE_URL = "https://api.github.com/";
    private static final String TAG = "GitHubRequestHelper";

    public InputStream getBaseUrl() {
        try {
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
                httpConnection.disconnect();
                return in;
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "Malformed URL Exception.", e);
        } catch (IOException e) {
            Log.e(TAG, "IO Exception.", e);
        }
        return null;
    }
}
