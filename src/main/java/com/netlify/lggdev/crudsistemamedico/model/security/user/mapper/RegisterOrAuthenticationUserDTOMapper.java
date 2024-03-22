package com.netlify.lggdev.crudsistemamedico.model.security.user.mapper;

import com.netlify.lggdev.crudsistemamedico.model.security.user.dto.RegisterOrAuthenticationUserDTO;
import com.netlify.lggdev.crudsistemamedico.model.security.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RegisterOrAuthenticationUserDTOMapper {

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "fullname", source = "fullName"),
            @Mapping(target = "password", ignore = true),
    })
    RegisterOrAuthenticationUserDTO toAuthenticationUser(User user);
    @Mappings({
            @Mapping(target = "userName", source = "username"),
            @Mapping(target = "fullName", source = "fullname"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "permissions", ignore = true)
    })
    User toUser(RegisterOrAuthenticationUserDTO registerOrAuthenticationUserDTO);

}
