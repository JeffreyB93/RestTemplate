package org.example.repository.mapper;

import org.example.model.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PhoneResultSetMapper {
    Phone map (ResultSet resultSet) throws SQLException;
}