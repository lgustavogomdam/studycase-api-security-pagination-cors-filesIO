package com.netlify.lggdev.crudsistemamedico.service;

import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteDTO;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteEntity;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteMapper;
import com.netlify.lggdev.crudsistemamedico.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class PacienteService extends GenericService<Long, PacienteEntity, PacienteDTO, PacienteMapper, PacienteRepository> {
    public PacienteService(PacienteRepository repository, PacienteMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void saveValidation(PacienteEntity entity) {

    }
}
