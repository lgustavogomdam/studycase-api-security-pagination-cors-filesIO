package com.netlify.lggdev.crudsistemamedico.model.security.user.dto;

public record RegisterOrAuthenticationUserDTO(
        String username,
        String fullname,
        String password
) {}
