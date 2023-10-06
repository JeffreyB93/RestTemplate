package org.example.repository;

import org.example.model.Role;

import java.util.Set;

public interface RoleRepository extends Repository<Role, Long> {
    Set<Role> findAll();
}
