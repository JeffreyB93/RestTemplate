package org.example.servlet.dto;

import java.util.Objects;
import java.util.Set;

public class RoleDto {

    private String roleName;
    private Set<UserDto> usersDto;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UserDto> getUsersDto() {
        return usersDto;
    }

    public void setUsersDto(Set<UserDto> usersDto) {
        this.usersDto = usersDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(roleName, roleDto.roleName) && Objects.equals(usersDto, roleDto.usersDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, usersDto);
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleName='" + roleName + '\'' +
                ", usersDto=" + usersDto +
                '}';
    }
}
