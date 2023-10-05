package org.example.service;

import org.example.model.Role;

import java.util.Set;

public interface RoleService extends Service<Role, Long> {
    Set<Role> findAll();
}