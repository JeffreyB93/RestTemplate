package org.example.repository.mapper;

import org.example.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserResultSetMapper {
    User map (ResultSet resultSet) throws SQLException;
}