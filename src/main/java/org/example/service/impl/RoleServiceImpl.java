package org.example.service.impl;

import org.example.model.Role;
import org.example.repository.RoleRepository;
import org.example.repository.impl.RoleRepositoryImpl;
import org.example.service.RoleService;

import java.util.Set;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository = new RoleRepositoryImpl();

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return roleRepository.deleteById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Set<Role> findAll() {
        return roleRepository.findAll();
    }
}
