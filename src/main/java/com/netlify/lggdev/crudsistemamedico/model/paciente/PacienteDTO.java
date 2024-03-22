package com.netlify.lggdev.crudsistemamedico.model.paciente;

import com.netlify.lggdev.crudsistemamedico.model.endereco.EnderecoEntity;

import java.util.List;

public record PacienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        List<EnderecoEntity> enderecoEntity
) {}
