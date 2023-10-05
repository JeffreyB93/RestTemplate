package org.example.repository.mapper;

import org.example.model.Phone;
import org.example.repository.mapper.impl.PhoneResultSetMapperImpl;
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
class PhoneResultSetMapperImplTest {

    @InjectMocks
    PhoneResultSetMapperImpl phoneResultSetMapper = new PhoneResultSetMapperImpl();
    @Mock
    ResultSet resultSet;

    @Test
    void map() throws SQLException {
        Phone phone = new Phone();
        phone.setPhoneId(1L);
        phone.setPhoneNumber("111");
        Mockito.when(resultSet.getLong("phoneId")).thenReturn(1L);
        Mockito.when(resultSet.getString("phoneNumber")).thenReturn("111");
        Assertions.assertEquals(phone, phoneResultSetMapper.map(resultSet));
    }
}