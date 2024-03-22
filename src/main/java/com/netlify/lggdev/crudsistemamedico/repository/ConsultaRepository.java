package com.netlify.lggdev.crudsistemamedico.repository;

import com.netlify.lggdev.crudsistemamedico.model.consulta.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends GenericRepository<ConsultaEntity, Long> {
}
