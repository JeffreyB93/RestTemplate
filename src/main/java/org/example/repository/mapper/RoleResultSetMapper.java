package org.example.repository.mapper;

import org.example.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoleResultSetMapper {
    Role map(ResultSet resultSet) throws SQLException;
}
