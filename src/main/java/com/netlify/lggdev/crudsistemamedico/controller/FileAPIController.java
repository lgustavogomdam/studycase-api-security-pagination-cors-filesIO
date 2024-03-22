package com.netlify.lggdev.crudsistemamedico.controller;

import com.netlify.lggdev.crudsistemamedico.model.file.UploadFileDTO;
import com.netlify.lggdev.crudsistemamedico.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/file")
public class FileAPIController {

    private final Logger logger = Logger.getLogger(FileAPIController.class.getName());

    private final FileStorageService service;

    @Autowired
    public FileAPIController(FileStorageService fileStorageService) {
        this.service = fileStorageService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<UploadFileDTO> uploadFile(@RequestParam(name = "file") MultipartFile file){

        var fileName = this.service.uploadFile(file);
        String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download-file/")
                .path(fileName)
                .toUriString();

        return new ResponseEntity<UploadFileDTO>(
                new UploadFileDTO(fileName,fileDownloadURI,file.getContentType(),file.getSize()),
                HttpStatus.OK
        );
    }

    @PostMapping("/upload-multiple-files")
    public ResponseEntity<List<UploadFileDTO>> uploadMultipleFile(@RequestParam(name = "files") MultipartFile[] files){

        return new ResponseEntity<List<UploadFileDTO>>(
                Arrays.asList(files)
                        .stream()
                        .map(file -> uploadFile(file).getBody())
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
    @GetMapping("/download-file/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request){

        Resource resource = this.service.loadFileAsResource(filename);
        String contentType = "";
        try {
             contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception ex){
            logger.info("Não conseguiu achar o arquivo!");
        }
        if (contentType.isBlank())
            contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
