package org.example.servlet.mapper;

import org.example.model.Role;
import org.example.model.User;
import org.example.servlet.dto.PhoneDto;
import org.example.servlet.dto.RoleDto;
import org.example.servlet.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class RoleDtoMapperImplTest {

    @Test
    void toDto() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Иван");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Иван2");


        role.setUsers(Set.of(user1, user2));
        RoleDto roleDto = RoleDtoMapper.INSTANCE.toDto(role);
        Assertions.assertEquals(role.getRoleName(), roleDto.getRoleName());
    }

    @Test
    void toEntity() {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName("Админ");

        UserDto userDto1 = new UserDto();
        userDto1.setName("Иван");

        UserDto userDto2 = new UserDto();
        userDto2.setName("Иван2");


        roleDto.setUsersDto(Set.of(userDto1, userDto2));
        Role role = RoleDtoMapper.INSTANCE.toEntity(roleDto);
        Assertions.assertEquals(role.getRoleName(), roleDto.getRoleName());
    }
}