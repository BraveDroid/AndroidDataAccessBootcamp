package com.bravedroid.dataaccess.fetching.local.file;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalFileHelper {
    public void createOrUpdateFile(Context context, String fileName, String messageData, boolean shouldUpdate) {
        int modePrivate = Context.MODE_PRIVATE;
        if (shouldUpdate) modePrivate = Context.MODE_APPEND;
        try (FileOutputStream fos = context.openFileOutput(fileName, modePrivate)) {
            fos.write(messageData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateFile(Context context, String fileName, String messageData) {
        createOrUpdateFile(context, fileName, messageData, false);
    }

    /*
    cache file must not have a size over than 1 MB
    */
    public void createOrUpdateCacheFile(Context context, String fileName, String messageData, boolean shouldUpdate) {
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(cacheFile, shouldUpdate)) {
            fos.write(messageData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateCacheFile(Context context, String fileName, String messageData) {
        createOrUpdateCacheFile(context, fileName, messageData, false);
    }

    public String readFileContent(Context context, String fileName) {
        StringBuffer stb = new StringBuffer();
        try (FileInputStream fis = context.openFileInput(fileName)) {
            int read;
            while ((read = fis.read()) != -1) {
                stb.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stb.toString();
    }

    public String readCacheFileContent(Context context, String fileName) {
        StringBuffer stb = new StringBuffer();
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir, fileName);
        try (FileInputStream fis = new FileInputStream(cacheFile)) {
            int read;
            while ((read = fis.read()) != -1) {
                stb.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stb.toString();
    }


    public String[] listNameFiles(Context context) {
        StringBuilder stb = new StringBuilder();
        String[] fileList = context.fileList();
        //String[] fileList = context.getFilesDir().list();

        for (String item : fileList) {
            stb.append(item).append(" ");
        }
        String allNames = stb.toString().trim();
        if (allNames.isEmpty()) {
            return new String[0];
        }
        String[] names = allNames.split(" ");
        return names;
    }

    public String[] listNameCacheFiles(Context context) {
        StringBuilder stb = new StringBuilder();
        String[] fileList = context.getCacheDir().list();
        for (String item : fileList) {
            stb.append(item).append(" ");
        }
        String allNames = stb.toString().trim();
        if (allNames.isEmpty()) {
            return new String[0];
        }
        return allNames.split(" ");
    }

    public boolean deleteFile(Context context, String fileName) {
        return context.deleteFile(fileName);
    }

    public boolean deleteCacheFile(Context context, String fileName) {
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir, fileName);
        return cacheFile.delete();
    }

    public void deleteAllInternalFiles(Context context) {
        String[] strings = context.fileList();
        for (String item : strings) {
            context.deleteFile(item);
        }
    }

    public void deleteAllInternalCacheFiles(Context context) {
        File cacheDir = context.getCacheDir();
        String[] strings = cacheDir.list();
        for (String fileName : strings) {
            File cacheFile = new File(cacheDir, fileName);
            cacheFile.delete();
        }
    }
}
