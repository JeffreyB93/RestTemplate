package org.example.service.impl;

import org.example.model.Role;
import org.example.repository.impl.RoleRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService = new RoleServiceImpl();
    @Mock
    private RoleRepositoryImpl roleRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");
        Mockito.when(roleRepository.findById(1L)).thenReturn(role);
        Assertions.assertEquals(role, roleService.findById(1L));
    }
    @Test
    void deleteById() {
        Mockito.when(roleRepository.deleteById(1L)).thenReturn(true);
        Assertions.assertTrue(roleService.deleteById(1L));
    }

    @Test
    void save() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Админ");
        Mockito.when(roleRepository.save(role)).thenReturn(role);
        Assertions.assertEquals(role, roleService.save(role));
    }

    @Test
    void findAll() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleName("Админ");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setRoleName("Клиент");
        Set<Role> roles = new HashSet<>(Set.of(role1, role2));

        Mockito.when(roleRepository.findAll()).thenReturn(roles);
        Assertions.assertEquals(roles, roleService.findAll());
    }
}