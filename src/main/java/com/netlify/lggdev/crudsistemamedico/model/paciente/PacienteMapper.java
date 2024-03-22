package com.netlify.lggdev.crudsistemamedico.model.paciente;

import com.netlify.lggdev.crudsistemamedico.model.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PacienteMapper extends GenericMapper<PacienteEntity, PacienteDTO> {
}
