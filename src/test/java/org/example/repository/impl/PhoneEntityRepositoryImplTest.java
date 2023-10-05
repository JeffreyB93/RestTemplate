package org.example.repository.impl;

import org.example.db.Impl.ConnectionManagerImpl;
import org.example.model.Phone;
import org.example.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.HashSet;
import java.util.Set;

class PhoneEntityRepositoryImplTest {

    private PhoneRepository phoneRepository = new PhoneRepositoryImpl();

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
        Long expected = 2L;
        Long actual = phoneRepository.findById(expected).getPhoneId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findById_null() {
        Assertions.assertNull(phoneRepository.findById(100L).getPhoneId());
    }

    @Test
    void deleteById_true() {
        Assertions.assertTrue(phoneRepository.deleteById(1L));
    }

    @Test
    void deleteById_false() {
        Assertions.assertFalse(phoneRepository.deleteById(300L));
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

        Set<Phone> expected = new HashSet<>();
        expected.add(phone1);
        expected.add(phone2);
        expected.add(phone3);
        expected.add(phone4);

        Set<Phone> phones = phoneRepository.findAll();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void save() {
        Phone expected = new Phone();
        expected.setPhoneId(1L);
        expected.setPhoneNumber("22222");

        Phone phone = phoneRepository.save(expected);
        Assertions.assertEquals(expected, phone);
    }
}