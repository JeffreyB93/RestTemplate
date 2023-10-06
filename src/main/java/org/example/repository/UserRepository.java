package org.example.repository;

import org.example.model.User;

import java.util.ArrayList;

public interface UserRepository extends Repository<User, Long> {
    ArrayList<User> findAll();
}