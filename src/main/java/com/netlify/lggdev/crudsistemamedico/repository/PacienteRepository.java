package com.netlify.lggdev.crudsistemamedico.repository;

import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends GenericRepository<PacienteEntity,Long>{
}
