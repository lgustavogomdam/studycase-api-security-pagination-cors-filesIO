package com.netlify.lggdev.crudsistemamedico.service;

import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoDTO;
import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoEntity;
import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoMapper;
import com.netlify.lggdev.crudsistemamedico.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService extends GenericService<Long, MedicoEntity, MedicoDTO, MedicoMapper, MedicoRepository> {
    public MedicoService(MedicoRepository repository, MedicoMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void saveValidation(MedicoEntity entity) {

    }
}
