package org.example.repository.impl;

import org.example.model.Role;
import org.example.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

class RoleRepositoryImplTest {

    RoleRepository roleRepository = new RoleRepositoryImpl();

    @Container
    public static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("postgres")
                    .withUsername("test")
                    .withInitScript("db-migration.SQL")
                    .withPassword("test");

    @BeforeAll
    public static void setUp() throws IOException {
        container.start();
        FileInputStream fis = new FileInputStream("src/main/resources/db.properties");
        Properties property = new Properties();
        property.load(fis);
        fis.close();
        property.setProperty("url", container.getJdbcUrl());
        property.setProperty("username", container.getUsername());
        property.setProperty("password", container.getPassword());

        FileOutputStream output = new FileOutputStream("src/main/resources/db.properties");
        property.store(output, "");
        output.close();
    }

    @Test
    void findById_1() throws SQLException {
        Long expected = 1L;
        Role role = roleRepository.findById(expected);
        Long actual = role.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findById_null() throws SQLException {
        Assertions.assertNull(roleRepository.findById(100L));
    }

    @Test
    void deleteById_true() {
        Assertions.assertTrue(roleRepository.deleteById(2L));
    }

    @Test
    void deleteById_false() {
        Assertions.assertFalse(roleRepository.deleteById(100L));
    }

    @Test
    void findAll() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleName("Админ");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setRoleName("Клиент");

        Set<Role> expected = new HashSet<>();//Set.of(role1, role2)
        expected.add(role1);
        expected.add(role2);
        Set<Role> roles = roleRepository.findAll();
        Assertions.assertEquals(expected, roles);
    }

    @Test
    void save() {
        Role expected = new Role();
        expected.setId(10L);
        expected.setRoleName("Гость");
        Assertions.assertEquals(expected, roleRepository.save(expected));
    }
}