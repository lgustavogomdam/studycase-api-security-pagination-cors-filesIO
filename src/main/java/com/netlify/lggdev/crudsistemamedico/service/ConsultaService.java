package com.netlify.lggdev.crudsistemamedico.service;

import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaEntity;
import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaDTO;
import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaMapper;
import com.netlify.lggdev.crudsistemamedico.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService extends GenericService<Long, ConsultaEntity, ConsultaDTO, ConsultaMapper, ConsultaRepository> {
    public ConsultaService(ConsultaRepository repository, ConsultaMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void saveValidation(ConsultaEntity entity) {

    }
}
