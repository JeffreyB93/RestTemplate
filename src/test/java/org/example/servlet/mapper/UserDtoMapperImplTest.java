package org.example.servlet.mapper;

import org.example.model.Phone;
import org.example.model.Role;
import org.example.model.User;
import org.example.servlet.dto.PhoneDto;
import org.example.servlet.dto.RoleDto;
import org.example.servlet.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class UserDtoMapperImplTest {

    @Test
    void toDto() {
        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("4444");

        Phone phone2 = new Phone();
        phone2.setPhoneId(1L);
        phone2.setPhoneNumber("5555");

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");

        User user = new User();
        user.setId(1L);
        user.setName("Иван");
        user.setPhones(Set.of(phone1, phone2));
        user.setRoles(Set.of(role));

        UserDto userDto = UserDtoMapper.INSTANCE.toDto(user);
        System.out.println(userDto.toString());
        Assertions.assertEquals(user.getName(), userDto.getName());
    }

    @Test
    void toEntity() {
        PhoneDto phoneDto1 = new PhoneDto();
        phoneDto1.setPhoneNumber("4444");

        PhoneDto phoneDto2 = new PhoneDto();
        phoneDto2.setPhoneNumber("5555");

        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName("Админ");

        UserDto userDto = new UserDto();
        userDto.setName("Иван");
        userDto.setPhonesDto(Set.of(phoneDto1, phoneDto2));
        userDto.setRolesDto(Set.of(roleDto));
        User user = UserDtoMapper.INSTANCE.toEntity(userDto);
        Assertions.assertEquals(user.getName(), userDto.getName());
    }
}