package com.bravedroid.dataaccess.fetching.local.file;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import java.io.*;

public class ExternalFileHelper {
    public static String STORAGE_DIR = "storage_dir";

    public void createOrUpdateExternalFile(Context context, String fileName, String messageData, boolean shouldUpdate) {
        File externalDir = context.getExternalFilesDir(STORAGE_DIR);
        File externalFile = new File(externalDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(externalFile, shouldUpdate)) {
            fos.write(messageData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateExternalFile(Context context, String fileName, String messageData) {
        createOrUpdateExternalFile(context, fileName, messageData, false);
    }

    public void createOrUpdatePublicExternalFile(String fileName, String messageData, boolean shouldUpdate) {
        File publicExternalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File externalFile = new File(publicExternalDir, fileName);
        try (FileOutputStream fos = new FileOutputStream(externalFile, shouldUpdate)) {
            fos.write(messageData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdatePublicExternalFile(String fileName, String messageData) {
        createOrUpdatePublicExternalFile(fileName, messageData, false);
    }

    public void createPublicExternalFile2(String fileName, String messageData) {
        File publicExternalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File externalFile = new File(publicExternalDir, fileName);
        writeTextFile(externalFile, messageData);
    }

    public void createOrUpdateExternalCacheFile(Context context, String fileName, String messageData, boolean shouldUpdate) {
        File externalCacheDir = context.getExternalCacheDir();
        File externalCacheFile = new File(externalCacheDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(externalCacheFile, shouldUpdate)) {
            fos.write(messageData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOrUpdateExternalCacheFile(Context context, String fileName, String messageData) {
        createOrUpdateExternalCacheFile(context, fileName, messageData, false);
    }

    public String readExternalFileContent(Context context, String fileName) {
        StringBuffer stb = new StringBuffer();
        File externalFileDir = context.getExternalFilesDir(STORAGE_DIR);
        File externalFile = new File(externalFileDir, fileName);
        try (FileInputStream fis = new FileInputStream(externalFile)) {
            int read;
            while ((read = fis.read()) != -1) {
                stb.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stb.toString();

    }

    public String readPublicExternalFileContent(String fileName) {
        StringBuffer stb = new StringBuffer();
        File publicExternalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File externalFile = new File(publicExternalDir, fileName);
        try (FileInputStream fis = new FileInputStream(externalFile)) {
            int read;
            while ((read = fis.read()) != -1) {
                stb.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stb.toString();
    }

    public String readPublicExternalFileContent2(String fileName) {
        File publicExternalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File externalFile = new File(publicExternalDir, fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        try (InputStream fis = new FileInputStream(externalFile)) {
            while ((fis.read(bytes)) > 0) {
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(outputStream.toByteArray());
    }

    public String readPublicExternalFileContent3(String fileName) {
        File publicExternalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File externalFile = new File(publicExternalDir, fileName);
        return readTextFile(externalFile);
    }


    public String readCacheExternalFileContent(Context context, String fileName) {
        StringBuffer stb = new StringBuffer();
        File externalCacheDir = context.getExternalCacheDir();
        File externalCacheFile = new File(externalCacheDir, fileName);
        try (FileInputStream fis = new FileInputStream(externalCacheFile)) {
            int read;
            while ((read = fis.read()) != -1) {
                stb.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stb.toString();
    }

    public String[] listNameExternalFiles(Context context) {
        StringBuilder stb = new StringBuilder();
        File[] externalFileList = context.getExternalFilesDir(STORAGE_DIR).listFiles();
        for (File item : externalFileList) {
            stb.append(item.getName()).append(" ");
        }
        String allNames = stb.toString().trim();
        if (allNames.isEmpty()) {
            return new String[0];
        }
        return allNames.split(" ");
    }

    public String[] listNamePublicExternalFiles() {
        StringBuilder stb = new StringBuilder();
        File[] externalFileList = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles();
        for (File item : externalFileList) {
            stb.append(item.getName()).append(" ");
        }
        String allNames = stb.toString().trim();
        if (allNames.isEmpty()) {
            return new String[0];
        }
        return allNames.split(" ");
    }

    public String[] listNameExternalCacheFiles(Context context) {
        StringBuilder stb = new StringBuilder();
        File[] externalCacheFileList = context.getExternalCacheDir().listFiles();
        for (File item : externalCacheFileList) {
            stb.append(item.getName()).append(" ");
        }
        String allNames = stb.toString().trim();
        if (allNames.isEmpty()) {
            return new String[0];
        }
        return allNames.split(" ");
    }

    public boolean deleteExternalFile(Context context, String fileName) {
        File externalFileDir = context.getExternalFilesDir(STORAGE_DIR);
        File externalFile = new File(externalFileDir, fileName);
        return externalFile.delete();
    }

    public boolean deletePublicExternalFile(String fileName) {
        File cacheDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File cacheFile = new File(cacheDir, fileName);
        return cacheFile.delete();
    }

    public boolean deleteExternalCacheFile(Context context, String fileName) {
        File cacheDir = context.getExternalCacheDir();
        File cacheFile = new File(cacheDir, fileName);
        return cacheFile.delete();
    }

    public void deleteAllExternalFiles(Context context) {
        File externalFilesDir = context.getExternalFilesDir(STORAGE_DIR);
        String[] strings = externalFilesDir.list();
        for (String item : strings) {
            File externalFile = new File(externalFilesDir, item);
            externalFile.delete();
        }
    }

    public void deleteAllExternalCacheFiles(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        String[] strings = externalCacheDir.list();
        for (String item : strings) {
            File externalFile = new File(externalCacheDir, item);
            externalFile.delete();
        }
    }

    public void deleteAllExternalPublicFiles() {
        File cacheDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String[] strings = cacheDir.list();
        for (String string : strings) {
            File cacheFile = new File(cacheDir, string);
            cacheFile.delete();
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public boolean isReadToExternalPublicStoragePermissionGranted(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);
    }

    public boolean isWriteToExternalPublicStoragePermissionGranted(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);
    }

    private void writeTextFile(File file, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readTextFile(File file) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}