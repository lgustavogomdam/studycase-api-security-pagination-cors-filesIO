package com.netlify.lggdev.crudsistemamedico.model.consulta;

import com.netlify.lggdev.crudsistemamedico.model.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface ConsultaMapper extends GenericMapper<ConsultaEntity,ConsultaDTO> {
}
