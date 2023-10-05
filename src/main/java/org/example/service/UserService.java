package org.example.service;

import org.example.model.User;

import java.util.ArrayList;

public interface UserService extends Service<User, Long> {
    ArrayList<User> findAll();
}