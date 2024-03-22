package com.netlify.lggdev.crudsistemamedico.controller;

import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteDTO;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteEntity;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteMapper;
import com.netlify.lggdev.crudsistemamedico.repository.PacienteRepository;
import com.netlify.lggdev.crudsistemamedico.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paciente")
public class PacienteAPIController extends GenericAPIController<Long, PacienteEntity, PacienteDTO, PacienteMapper, PacienteRepository, PacienteService> {
    public PacienteAPIController(PacienteService service) {
        super(service);
    }
}
