package org.example.repository.mapper;

import org.example.model.Phone;
import org.example.model.Role;
import org.example.repository.mapper.impl.PhoneResultSetMapperImpl;
import org.example.repository.mapper.impl.RoleResultSetMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class RoleResultSetMapperImplTest {

    @InjectMocks
    RoleResultSetMapperImpl roleResultSetMapper = new RoleResultSetMapperImpl();
    @Mock
    ResultSet resultSet;

    @Test
    void map() throws SQLException {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");
        Mockito.when(resultSet.getLong("roleId")).thenReturn(1L);
        Mockito.when(resultSet.getString("roleName")).thenReturn("Админ");
        Assertions.assertEquals(role, roleResultSetMapper.map(resultSet));
    }
}