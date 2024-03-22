package com.netlify.lggdev.crudsistemamedico.service;

import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoDTO;
import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;
import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoMapper;
import com.netlify.lggdev.crudsistemamedico.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends GenericService<Long, EnderecoEntity, EnderecoDTO, EnderecoMapper, EnderecoRepository> {
    public EnderecoService(EnderecoRepository repository, EnderecoMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void saveValidation(EnderecoEntity entity) {

    }
}
