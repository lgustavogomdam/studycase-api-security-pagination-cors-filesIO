package com.netlify.lggdev.crudsistemamedico.model.consulta;

import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoDTO;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteDTO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record ConsultaDTO(
        @NotNull
        @Future
        Date data,
        @NotNull
        MedicoDTO medico,
        @NotNull
        PacienteDTO paciente
) { }
