package org.example.servlet.mapper;

import org.example.model.User;
import org.example.servlet.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "phones", target = "phonesDto")
    @Mapping(source = "roles", target = "rolesDto")
    UserDto toDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "phonesDto", target = "phones")
    @Mapping(source = "rolesDto", target = "roles")
    User toEntity(UserDto userDto);

}
