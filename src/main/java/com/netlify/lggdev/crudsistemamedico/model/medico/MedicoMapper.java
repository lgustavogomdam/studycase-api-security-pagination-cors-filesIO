package com.netlify.lggdev.crudsistemamedico.model.medico;

import com.netlify.lggdev.crudsistemamedico.model.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface MedicoMapper extends GenericMapper<MedicoEntity, MedicoDTO> {
}
