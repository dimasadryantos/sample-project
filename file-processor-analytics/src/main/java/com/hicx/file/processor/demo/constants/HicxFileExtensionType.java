package com.hicx.file.processor.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.FileSystemNotFoundException;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum HicxFileExtensionType {


    TXT("txt"),
    PDF("pdf"),
    DOC("doc");

    private final String supportFileName;

    public static HicxFileExtensionType checkSupportFile(String extension) {
        return Stream.of(HicxFileExtensionType.values())
                .filter(ext -> ext.supportFileName.equals(extension))
                .findFirst()
                .orElseThrow(() -> new FileSystemNotFoundException("File with extension " + extension + "is not suppoerted "));

    }


}
