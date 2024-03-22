package com.netlify.lggdev.crudsistemamedico.model.security.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netlify.lggdev.crudsistemamedico.model.security.permission.Permission;

import java.util.List;

public record RegisterAuthorityUserDTO(
        String username,
        String fullname,
        String password,
        List<Permission> permissions
) {}
