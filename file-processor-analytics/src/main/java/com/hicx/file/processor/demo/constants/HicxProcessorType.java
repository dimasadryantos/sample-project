package com.hicx.file.processor.demo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * in case we have another processor in the future
 */
@AllArgsConstructor
@Getter
public enum HicxProcessorType {

    FILE_PROCESSOR("FileProcessor");

    private final String processorName;

    public static HicxProcessorType fromProcessorName(String processorName) {
        return Stream.of(HicxProcessorType.values())
                .filter(appType -> appType.processorName.equals(processorName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Apps processor "
                        + processorName + " is not found"));
    }


}
