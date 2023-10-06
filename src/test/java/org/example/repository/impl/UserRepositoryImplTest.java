package org.example.repository.impl;

import org.example.db.Impl.ConnectionManagerImpl;
import org.example.model.Phone;
import org.example.model.Role;
import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class UserRepositoryImplTest {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Container
    public static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("postgres")
                    .withUsername("test")
                    .withInitScript("db-migration.SQL")
                    .withPassword("test");

    @BeforeAll
    public static void setUp() {
        container.start();
        ConnectionManagerImpl.setDbUrl(container.getJdbcUrl());
        ConnectionManagerImpl.setBdUserName(container.getUsername());
        ConnectionManagerImpl.setBdPassword(container.getPassword());
    }

    @Test
    void findById_1() {
        User user = userRepository.findById(1L);
        Assertions.assertEquals(1L, user.getId());
    }

    @Test
    void findById_null() {
        Assertions.assertNull(userRepository.findById(100L));
    }

    @Test
    void deleteById_true() {
        Assertions.assertTrue(userRepository.deleteById(2L));
    }

    @Test
    void deleteById_false() {
        Assertions.assertFalse(userRepository.deleteById(300L));
    }

    @Test
    void findAll() {
        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("7894234");

        Phone phone2 = new Phone();
        phone2.setPhoneId(2L);
        phone2.setPhoneNumber("5779757");

        Phone phone3 = new Phone();
        phone3.setPhoneId(3L);
        phone3.setPhoneNumber("124576");

        Phone phone4 = new Phone();
        phone4.setPhoneId(4L);
        phone4.setPhoneNumber("273874");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleName("Админ");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setRoleName("Клиент");

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Коля");
        user1.setRoles(Set.of(role1, role2));
        user1.setPhones(Set.of(phone1, phone2));

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Катя");
        user2.setRoles(Set.of(role1));
        user2.setPhones(Set.of(phone3));

        User user3 = new User();
        user3.setId(3L);
        user3.setName("Виталик");
        user3.setRoles(Set.of(role2));
        user3.setPhones(Set.of(phone4));

        ArrayList<User> expected = new ArrayList<>(List.of(user1, user2, user3));
        ArrayList<User> users = userRepository.findAll();
        Assertions.assertEquals(expected, users);
    }

    @Test
    void save() {
        User expected = new User();
        expected.setId(10L);
        expected.setName("Боря");
        Assertions.assertEquals(expected, userRepository.save(expected));
    }
}