package org.example.servlet.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.model.Role;
import org.example.model.User;
import org.example.servlet.dto.RoleDto;
import org.example.servlet.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-06T16:50:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class RoleDtoMapperImpl implements RoleDtoMapper {

    @Override
    public RoleDto toDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleName( role.getRoleName() );
        roleDto.setUsersDto( userSetToUserDtoSet( role.getUsers() ) );

        return roleDto;
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleName( roleDto.getRoleName() );
        role.setUsers( userDtoSetToUserSet( roleDto.getUsersDto() ) );

        return role;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getName() );

        return userDto;
    }

    protected Set<UserDto> userSetToUserDtoSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserDto> set1 = new LinkedHashSet<UserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserDto( user ) );
        }

        return set1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDto.getName() );

        return user;
    }

    protected Set<User> userDtoSetToUserSet(Set<UserDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<User> set1 = new LinkedHashSet<User>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserDto userDto : set ) {
            set1.add( userDtoToUser( userDto ) );
        }

        return set1;
    }
}
