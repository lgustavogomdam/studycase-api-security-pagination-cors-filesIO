package com.netlify.lggdev.crudsistemamedico.controller;

import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoDTO;
import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;
import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoMapper;
import com.netlify.lggdev.crudsistemamedico.repository.EnderecoRepository;
import com.netlify.lggdev.crudsistemamedico.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoAPIController extends GenericAPIController<Long, EnderecoEntity, EnderecoDTO, EnderecoMapper, EnderecoRepository, EnderecoService> {
    public EnderecoAPIController(EnderecoService service) {
        super(service);
    }
}
