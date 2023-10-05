package org.example.service.impl;

import org.example.model.Phone;
import org.example.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PhoneServiceImplTest {

    @InjectMocks
    private PhoneServiceImpl phoneService = new PhoneServiceImpl();
    @Mock
    private PhoneRepository phoneRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        Phone phone = new Phone();
        phone.setPhoneId(1L);
        phone.setPhoneNumber("111");
        Mockito.when(phoneRepository.findById(1L)).thenReturn(phone);
        Assertions.assertEquals(phone, phoneService.findById(1L));
    }
    @Test
    void deleteById() {
        Mockito.when(phoneRepository.deleteById(1L)).thenReturn(true);
        Assertions.assertTrue(phoneService.deleteById(1L));
    }

    @Test
    void save() {
        Phone phone = new Phone();
        phone.setPhoneNumber("111");
        Mockito.when(phoneRepository.save(phone)).thenReturn(phone);
        Assertions.assertEquals(phone, phoneService.save(phone));
    }

    @Test
    void findAll() {
        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("111");
        Phone phone2 = new Phone();
        phone2.setPhoneId(2L);
        phone2.setPhoneNumber("222");
        Set<Phone> phones = new HashSet<>(Set.of(phone1, phone2));
        Mockito.when(phoneRepository.findAll()).thenReturn(phones);
        Assertions.assertEquals(phones, phoneService.findAll());
    }
}