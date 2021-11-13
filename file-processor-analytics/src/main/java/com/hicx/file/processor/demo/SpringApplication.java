package com.hicx.file.processor.demo;

import com.hicx.file.processor.demo.config.properties.ApplicationProperties;
import com.hicx.file.processor.demo.constants.HicxProcessorType;
import com.hicx.file.processor.demo.processor.fileprocessor.FileProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@Log4j2
@RequiredArgsConstructor
public class SpringApplication implements CommandLineRunner {

    private final ApplicationProperties properties;

    private final FileProcessor fileProcessor;

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String processorName = properties.getProcessorName();
        log.info("EXCUTE COMMAND LINE RUNNER===== " + processorName + properties.getProfile() + properties.getClientId());
        HicxProcessorType processorType = HicxProcessorType.fromProcessorName(processorName);
        runProcessor(processorType, args);
    }


    /**
     * Method to select and perform the execution  strategy of the selected processor type
     *
     * @param hicxProcessorType
     * @param args
     */
    private void runProcessor(HicxProcessorType hicxProcessorType, String[] args) {
        if (hicxProcessorType == HicxProcessorType.FILE_PROCESSOR) {
            fileProcessor.execute(String.valueOf(args[0]));
        }
    }
}
