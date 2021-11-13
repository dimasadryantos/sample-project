package com.hicx.file.processor.demo.processor.fileprocessor;

import com.hicx.file.processor.demo.config.properties.ApplicationProperties;
import com.hicx.file.processor.demo.constants.HicxFileExtensionType;
import com.hicx.file.processor.demo.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Log4j2
public class FileProcessorAction implements FileProcessor {

    private final ApplicationProperties applicationProperties;

    @Override
    public void execute(String input) {
        String[] filePath = input.split("/");
        String[] fileName = filePath[filePath.length - 1].split("\\.");
        String fileExtension = fileName[fileName.length - 1];
        checkFileExtension(fileExtension);
        processAndReadFileStatistic(input);
    }


    @SneakyThrows
    private void processAndReadFileStatistic(String filePath) {
        File file = FileUtils.getFile(applicationProperties.getLocalFilePath());
        if (!file.exists()) {
            throw new NoSuchFieldException("File not found in Local");
        }

        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader bufferedReader = new BufferedReader(input);
        log.info("TOTAL CHARACTER IN FILE : " + getCharCount(bufferedReader));

        moveFileAndCreateDirectory(filePath);
    }

    @SneakyThrows
    private void moveFileAndCreateDirectory(String filePath) {
        String processFolder = filePath + "/process";
        FileUtils.createFolder(processFolder);
        FileUtils.copyFile(filePath, processFolder);
    }


    public static int getCharCount(BufferedReader bufferedReader) throws IOException {
        int charCount = 0;
        String data;
        while ((data = bufferedReader.readLine()) != null) {
            charCount += data.length();
        }
        return charCount;
    }

    private void checkFileExtension(String fileExtension) {
        HicxFileExtensionType.checkSupportFile(fileExtension);
    }


}
