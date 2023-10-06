package org.example.servlet.mapper;

import org.example.model.Role;
import org.example.servlet.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleDtoMapper {
    RoleDtoMapper INSTANCE = Mappers.getMapper(RoleDtoMapper.class);

    @Mapping(source = "roleName", target = "roleName")
    @Mapping(source = "users", target = "usersDto")
    RoleDto toDto(Role role);

    @Mapping(source = "roleName", target = "roleName")
    @Mapping(source = "usersDto", target = "users")
    Role toEntity(RoleDto roleDto);

}
