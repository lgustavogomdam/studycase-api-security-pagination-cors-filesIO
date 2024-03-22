package com.netlify.lggdev.crudsistemamedico.controller;

import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoDTO;
import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoEntity;
import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoMapper;
import com.netlify.lggdev.crudsistemamedico.repository.MedicoRepository;
import com.netlify.lggdev.crudsistemamedico.service.MedicoService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medico")
//@Secured("ROLE_ADMIN") //Autenticação de role por annotation
public class MedicoAPIController extends GenericAPIController<Long, MedicoEntity, MedicoDTO, MedicoMapper, MedicoRepository,MedicoService> {
    public MedicoAPIController(MedicoService service) {
        super(service);
    }
}
