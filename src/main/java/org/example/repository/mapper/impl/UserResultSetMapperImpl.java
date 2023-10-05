package org.example.repository.mapper.impl;

import org.example.model.User;
import org.example.repository.mapper.UserResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapperImpl implements UserResultSetMapper {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("userId"));
        user.setName(resultSet.getString("userName"));
        return user;
    }
}