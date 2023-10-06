package org.example.servlet.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.model.Phone;
import org.example.model.Role;
import org.example.model.User;
import org.example.servlet.dto.PhoneDto;
import org.example.servlet.dto.RoleDto;
import org.example.servlet.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-06T16:50:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getName() );
        userDto.setPhonesDto( phoneSetToPhoneDtoSet( user.getPhones() ) );
        userDto.setRolesDto( roleSetToRoleDtoSet( user.getRoles() ) );

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDto.getName() );
        user.setPhones( phoneDtoSetToPhoneSet( userDto.getPhonesDto() ) );
        user.setRoles( roleDtoSetToRoleSet( userDto.getRolesDto() ) );

        return user;
    }

    protected PhoneDto phoneToPhoneDto(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDto phoneDto = new PhoneDto();

        phoneDto.setPhoneNumber( phone.getPhoneNumber() );

        return phoneDto;
    }

    protected Set<PhoneDto> phoneSetToPhoneDtoSet(Set<Phone> set) {
        if ( set == null ) {
            return null;
        }

        Set<PhoneDto> set1 = new LinkedHashSet<PhoneDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Phone phone : set ) {
            set1.add( phoneToPhoneDto( phone ) );
        }

        return set1;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleName( role.getRoleName() );

        return roleDto;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDto( role ) );
        }

        return set1;
    }

    protected Phone phoneDtoToPhone(PhoneDto phoneDto) {
        if ( phoneDto == null ) {
            return null;
        }

        Phone phone = new Phone();

        phone.setPhoneNumber( phoneDto.getPhoneNumber() );

        return phone;
    }

    protected Set<Phone> phoneDtoSetToPhoneSet(Set<PhoneDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Phone> set1 = new LinkedHashSet<Phone>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PhoneDto phoneDto : set ) {
            set1.add( phoneDtoToPhone( phoneDto ) );
        }

        return set1;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleName( roleDto.getRoleName() );

        return role;
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleDtoToRole( roleDto ) );
        }

        return set1;
    }
}
