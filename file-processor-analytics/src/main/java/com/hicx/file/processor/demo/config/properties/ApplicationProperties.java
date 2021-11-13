package com.hicx.file.processor.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "app")
@ToString
public class ApplicationProperties {

    private String profile;

    private int clientId;

    private String processorName;

    private String localFilePath;


}
