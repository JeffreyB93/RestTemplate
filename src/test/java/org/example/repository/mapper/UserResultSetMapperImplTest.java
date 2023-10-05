package org.example.repository.mapper;

import org.example.model.User;
import org.example.repository.mapper.impl.UserResultSetMapperImpl;
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
class UserResultSetMapperImplTest {

    @InjectMocks
    UserResultSetMapperImpl userResultSetMapper = new UserResultSetMapperImpl();
    @Mock
    ResultSet resultSet;

    @Test
    void map() throws SQLException {
        User user = new User();
        user.setId(1L);
        user.setName("Катя");
        Mockito.when(resultSet.getLong("userId")).thenReturn(1L);
        Mockito.when(resultSet.getString("userName")).thenReturn("Катя");
        Assertions.assertEquals(user, userResultSetMapper.map(resultSet));
    }
}