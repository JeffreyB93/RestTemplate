package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.Impl.ConnectionManagerImpl;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.RoleRepository;
import org.example.repository.mapper.RoleResultSetMapper;
import org.example.repository.mapper.UserResultSetMapper;
import org.example.repository.mapper.impl.RoleResultSetMapperImpl;
import org.example.repository.mapper.impl.UserResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RoleRepositoryImpl implements RoleRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private RoleResultSetMapper roleResultSetMapper = new RoleResultSetMapperImpl();
    private UserResultSetMapper userResultSetMapper = new UserResultSetMapperImpl();

    @Override
    public Role findById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * " +
                "FROM public.role " +
                "join public.userRole on public.role.roleId = public.userRole.role_id " +
                "join public.user on public.userRole.user_id = public.user.userId " +
                "where role.roleId = ?;";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = roleResultSetMapper.map(resultSet);
                Set<User> users = new HashSet<>();
                User user = userResultSetMapper.map(resultSet);
                users.add(user);
                while (resultSet.next()) {
                    user = userResultSetMapper.map(resultSet);
                    users.add(user);
                }
                role.setUsers(users);
                return role;
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error preparedStatement.close()");
                }
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM public.\"role\" WHERE roleid = ?";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error preparedStatement.close()");
                }
            }
        }
    }

    @Override
    public Set<Role> findAll() {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.role";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = roleResultSetMapper.map(resultSet);
                Set<Role> roles = new HashSet<>(Set.of(role));
                while (resultSet.next())
                    roles.add(roleResultSetMapper.map(resultSet));
                return roles;
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error preparedStatement.close()");
                }
            }
        }
    }

    @Override
    public Role save(Role role) {
        PreparedStatement preparedStatement = null;
        String sql = "insert into public.role(roleName) values (?)";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleName());
            if (preparedStatement.executeUpdate() == 1) {
                return role;
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Error preparedStatement.close()");
                }
            }
        }
    }
}
