package com.netlify.lggdev.crudsistemamedico.model.medico;

import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;
import com.netlify.lggdev.crudsistemamedico.enums.Especialidade;

import java.util.List;

public record MedicoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String CRM,
        Especialidade especialidade,
        List<EnderecoEntity> enderecoEntity
) { }
