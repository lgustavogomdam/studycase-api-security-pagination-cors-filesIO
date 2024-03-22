package com.netlify.lggdev.crudsistemamedico.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public record UploadFileDTO(
        String fileName,
        String fileDownloadURI,
        String fileType,
        Long size
) implements Serializable {
    private static final long SerialVersionUID = 1L;
}
