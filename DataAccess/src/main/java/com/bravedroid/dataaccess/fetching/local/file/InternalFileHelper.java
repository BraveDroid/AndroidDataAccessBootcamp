package com.bravedroid.dataaccess.fetching.local.file;

import android.content.Context;

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

    public String getAbsoluteInternalFilePath(Context context) {
        return "" + context.getFilesDir();
    }

    public String[] listNameFiles(Context context) {
        StringBuilder stb = new StringBuilder();
        String[] fileList = context.fileList();
        for (String item : fileList) {
            stb.append(item).append(" ");
        }
        //File[] files = context.getFilesDir().listFiles();
        //for (File f : files) {
        //    stb.append(f.getName()).append(" ");
        //}

        String allNames = stb.toString().trim();
        if (allNames.isEmpty()) {
            return new String[0];
        }
        String[] names = allNames.split(" ");
        return names;
    }

    public boolean deleteFile(Context context, String fileName) {
        return context.deleteFile(fileName);
    }

    public void deleteAllInternalFiles(Context context) {
        String[] strings = context.fileList();
        for (String item : strings) {
            context.deleteFile(item);
        }
    }
}
