package com.hicx.file.processor.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    /**
     * Get file in folder input using directory
     *
     * @param folderPath
     * @return
     */
    public static File getFile(String folderPath) {
        List<File> files = new ArrayList<>();
        File targetFolder = new File(folderPath);
        File[] targetFiles = targetFolder.listFiles();
        if (targetFiles != null) {
            files.addAll(Arrays.asList(targetFiles));
        }
        return files.get(0);
    }


    public static void createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void copyFile(String sourceFilePath, String destFilePath) throws IOException {
        File source = new File(sourceFilePath);
        File dest = new File(destFilePath);

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
}
