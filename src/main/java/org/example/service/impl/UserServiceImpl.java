package org.example.service.impl;

import org.example.model.User;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.service.UserService;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public ArrayList<User> findAll() {
        return userRepository.findAll();
    }
}
