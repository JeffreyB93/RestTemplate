package org.example.servlet.dto;

import java.util.Objects;
import java.util.Set;

public class UserDto {

    private String name;
    private Set<PhoneDto> phonesDto;
    private Set<RoleDto> rolesDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PhoneDto> getPhonesDto() {
        return phonesDto;
    }

    public void setPhonesDto(Set<PhoneDto> phonesDto) {
        this.phonesDto = phonesDto;
    }

    public Set<RoleDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(Set<RoleDto> rolesDto) {
        this.rolesDto = rolesDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) && Objects.equals(phonesDto, userDto.phonesDto) && Objects.equals(rolesDto, userDto.rolesDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phonesDto, rolesDto);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", phonesDto=" + phonesDto +
                ", rolesDto=" + rolesDto +
                '}';
    }
}
