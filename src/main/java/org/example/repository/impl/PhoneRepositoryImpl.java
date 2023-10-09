package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.Impl.ConnectionManagerImpl;
import org.example.model.Phone;
import org.example.repository.PhoneRepository;
import org.example.repository.mapper.PhoneResultSetMapper;
import org.example.repository.mapper.impl.PhoneResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PhoneRepositoryImpl implements PhoneRepository {

    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private PhoneResultSetMapper resultSetMapper = new PhoneResultSetMapperImpl();

    @Override
    public Phone findById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"phone\" WHERE \"phoneid\" = ?";

        try {
            Connection connection = connectionManager.getConnection();
            //System.out.println(connection.getAutoCommit());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSetMapper.map(resultSet);
            } else return new Phone();
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
        String sql = "DELETE FROM public.\"phone\" WHERE \"phoneid\" = ?";
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
    public Set<Phone> findAll() {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM public.\"phone\"";
        Set<Phone> phones = new HashSet<>();
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                phones.add(resultSetMapper.map(resultSet));
            }
            return phones;
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
    public Phone save(Phone phone) {
        PreparedStatement preparedStatement = null;
        String sql = "insert into public.phone(phoneNumber) values (?)";
        try {
            Connection connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone.getPhoneNumber());
            if (preparedStatement.executeUpdate() == 1) {
                return phone;
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
