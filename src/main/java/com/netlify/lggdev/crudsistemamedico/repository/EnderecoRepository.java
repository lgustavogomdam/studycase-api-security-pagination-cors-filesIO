package com.netlify.lggdev.crudsistemamedico.repository;

import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends GenericRepository<EnderecoEntity, Long> {
}
