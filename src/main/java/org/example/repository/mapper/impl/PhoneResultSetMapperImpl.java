package org.example.repository.mapper.impl;

import org.example.model.Phone;
import org.example.repository.mapper.PhoneResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneResultSetMapperImpl implements PhoneResultSetMapper {

    @Override
    public Phone map(ResultSet resultSet) throws SQLException {
        Phone phone = new Phone();
        phone.setPhoneId(resultSet.getLong("phoneId"));
        phone.setPhoneNumber(resultSet.getString("phoneNumber"));
        return phone;
    }
}
