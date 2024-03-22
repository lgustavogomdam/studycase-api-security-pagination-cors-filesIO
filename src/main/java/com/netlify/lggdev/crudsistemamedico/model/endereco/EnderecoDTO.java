package com.netlify.lggdev.crudsistemamedico.model.endereco;

import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoEntity;
import com.netlify.lggdev.crudsistemamedico.model.paciente.PacienteEntity;
import com.netlify.lggdev.crudsistemamedico.enums.UFBrasil;
import jakarta.annotation.Nullable;

public record EnderecoDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        UFBrasil UF,
        String CEP,
        @Nullable
        MedicoEntity medicoEntity,
        @Nullable
        PacienteEntity PacienteEntity
) { }
