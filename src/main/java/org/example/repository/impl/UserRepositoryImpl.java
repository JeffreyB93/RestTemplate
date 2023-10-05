package org.example.repository.impl;


import org.example.db.ConnectionManager;
import org.example.db.Impl.ConnectionManagerImpl;
import org.example.model.Phone;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.mapper.PhoneResultSetMapper;
import org.example.repository.mapper.RoleResultSetMapper;
import org.example.repository.mapper.UserResultSetMapper;
import org.example.repository.mapper.impl.PhoneResultSetMapperImpl;
import org.example.repository.mapper.impl.RoleResultSetMapperImpl;
import org.example.repository.mapper.impl.UserResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {

    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private UserResultSetMapper userResultSetMapper = new UserResultSetMapperImpl();
    private PhoneResultSetMapper phoneResultSetMapper = new PhoneResultSetMapperImpl();
    private RoleResultSetMapper roleResultSetMapper = new RoleResultSetMapperImpl();

    @Override
    public User findById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * " +
                "FROM public.user " +
                "join public.phone on public.user.userId = public.phone.user_id " +
                "join public.userRole on public.user.userId = public.userRole.user_id " +
                "join public.role on public.userRole.role_id = public.role.roleId " +
                "where \"user\".userid = ?;";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user =  userResultSetMapper.map(resultSet);
                Set<Phone> phones = new HashSet<>();
                Set<Role> roles = new HashSet<>();
                Phone phone = phoneResultSetMapper.map(resultSet);
                Role role = roleResultSetMapper.map(resultSet);
                phones.add(phone);
                roles.add(role);
                while (resultSet.next()) {
                    phone = phoneResultSetMapper.map(resultSet);
                    role = roleResultSetMapper.map(resultSet);
                    phones.add(phone);
                    roles.add(role);
                }
                user.setPhones(phones);
                user.setRoles(roles);
                return user;
            } else return null;//throw new NullPointerException("User не найден");
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
        String sql = "DELETE FROM public.\"user\" WHERE \"userid\" = ?";
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
    public ArrayList<User> findAll() {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * " +
                "FROM public.user " +
                "join public.phone on public.user.userId = public.phone.user_id " +
                "join public.userRole on public.user.userId = public.userRole.user_id " +
                "join public.role on public.userRole.role_id = public.role.roleId";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                Long id = userResultSetMapper.map(resultSet).getId();
                if (users.size() < id) {
                    //add user
                    User user = userResultSetMapper.map(resultSet);
                    Set<Phone> phones = new HashSet<>();
                    Set<Role> roles = new HashSet<>();
                    Phone phone = phoneResultSetMapper.map(resultSet);
                    Role role = roleResultSetMapper.map(resultSet);
                    phones.add(phone);
                    roles.add(role);
                    user.setPhones(phones);
                    user.setRoles(roles);
                    users.add(user);
                } else {
                    Phone phone = phoneResultSetMapper.map(resultSet);
                    Set<Phone> getPhones = users.get((int) (id - 1)).getPhones();
                    getPhones.add(phone);
                    users.get((int) (id - 1)).setPhones(getPhones);

                    Role role = roleResultSetMapper.map(resultSet);
                    Set<Role> getRoles = users.get((int) (id - 1)).getRoles();
                    getRoles.add(role);
                    users.get((int) (id - 1)).setRoles(getRoles);
                }
            }
            return users;
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
    public User save(User user) {
        PreparedStatement preparedStatement = null;
        String sql = "insert into public.user(userName) values (?)";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            if (preparedStatement.executeUpdate() == 1) {
                return user;
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