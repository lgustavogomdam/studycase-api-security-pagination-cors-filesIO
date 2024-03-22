package com.netlify.lggdev.crudsistemamedico.infra.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfiguration {

    private String uploadDir;

    public String getUploadDir() {
        return this.uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
