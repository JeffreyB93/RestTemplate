package org.example.repository.mapper.impl;

import org.example.model.Role;
import org.example.repository.mapper.RoleResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultSetMapperImpl implements RoleResultSetMapper {

    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("roleId"));
        role.setRoleName(resultSet.getString("roleName"));
        return role;
    }
}
