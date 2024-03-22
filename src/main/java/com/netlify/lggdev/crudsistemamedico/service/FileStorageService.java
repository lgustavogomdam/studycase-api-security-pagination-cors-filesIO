package com.netlify.lggdev.crudsistemamedico.service;

import com.netlify.lggdev.crudsistemamedico.infra.configuration.FileStorageConfiguration;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.CustomFileNotFoundException;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService{
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfiguration fileStorageConfiguration) {

        this.fileStorageLocation = Paths.get(fileStorageConfiguration.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectory(this.fileStorageLocation);
        }catch (Exception ex){
            throw new FileStorageException("Local de armazenamento de arquivos não pode ser criado!", ex);
        }
    }

    public String uploadFile(MultipartFile file){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if (fileName.contains(".."))
                throw new FileStorageException("A extensão do arquivo especificado não é válida!");

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception ex){
            throw new FileStorageException("Não foi possível encontrar o arquivo especificado!");
        }
    }

    public Resource loadFileAsResource(String fileName){

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists())
                return resource;
            else
                throw new CustomFileNotFoundException("Arquivo não encontrado!");
        }catch (Exception ex){
            throw new CustomFileNotFoundException("Arquivo não encontrado!", ex.getCause());
        }
    }
}
