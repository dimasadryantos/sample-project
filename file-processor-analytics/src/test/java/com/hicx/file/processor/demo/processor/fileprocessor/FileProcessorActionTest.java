package com.hicx.file.processor.demo.processor.fileprocessor;

import com.hicx.file.processor.demo.config.properties.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystemNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileProcessorActionTest {

    private FileProcessorAction fileProcessorAction;
    private ApplicationProperties applicationProperties;

    @BeforeEach
    void setUp() {
        fileProcessorAction = new FileProcessorAction(applicationProperties);
    }


    @Test
    void itShouldThrowExceptionWhenFileNotSupported() {
        //given
        String input = "/local/data/example.csv";
        String[] filePath = input.split("/");
        String[] fileName = filePath[filePath.length - 1].split("\\.");
        String fileExtension = fileName[fileName.length - 1];

        //when //then
        assertThatThrownBy(() -> fileProcessorAction.execute(input))
                .hasMessageContaining("File with extension " + fileExtension + "is not suppoerted ")
                .isInstanceOf(FileSystemNotFoundException.class);
    }
}