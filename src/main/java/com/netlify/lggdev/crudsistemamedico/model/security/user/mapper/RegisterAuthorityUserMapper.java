package com.netlify.lggdev.crudsistemamedico.model.security.user.mapper;

import com.netlify.lggdev.crudsistemamedico.model.security.user.dto.RegisterAuthorityUserDTO;
import com.netlify.lggdev.crudsistemamedico.model.security.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RegisterAuthorityUserMapper {
    @Mappings({
        @Mapping(target = "userName", source = "username"),
        @Mapping(target = "fullName", source = "fullname"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "permissions", source = "permissions")
    })
    User toUser(RegisterAuthorityUserDTO registerAuthorityUserDTO);

    @Mappings({
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "fullname", source = "fullName"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "permissions", source = "permissions")
    })
    RegisterAuthorityUserDTO toModel(User user);
}
