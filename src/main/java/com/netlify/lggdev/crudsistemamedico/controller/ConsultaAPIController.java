package com.netlify.lggdev.crudsistemamedico.controller;

import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaDTO;
import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaEntity;
import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaMapper;
import com.netlify.lggdev.crudsistemamedico.repository.ConsultaRepository;
import com.netlify.lggdev.crudsistemamedico.service.ConsultaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaAPIController extends GenericAPIController<Long, ConsultaEntity, ConsultaDTO, ConsultaMapper, ConsultaRepository, ConsultaService>{
    public ConsultaAPIController(ConsultaService service) {
        super(service);
    }
}
