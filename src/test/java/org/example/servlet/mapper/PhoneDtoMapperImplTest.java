package org.example.servlet.mapper;

import org.example.model.Phone;
import org.example.servlet.dto.PhoneDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class PhoneDtoMapperImplTest {

    @Test
    void toDto() {
        Phone phone = new Phone();
        phone.setPhoneId(1L);
        phone.setPhoneNumber("4444");
        PhoneDto phoneDto = PhoneDtoMapper.INSTANCE.toDto(phone);
        Assertions.assertEquals(phone.getPhoneNumber(), phoneDto.getPhoneNumber());
    }

    @Test
    void toEntity() {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setPhoneNumber("4444");
        Phone phone = PhoneDtoMapper.INSTANCE.toEntity(phoneDto);
        Assertions.assertEquals(phoneDto.getPhoneNumber(), phone.getPhoneNumber());
    }

    /*@Test
    void toDtos() {
        Phone phone1 = new Phone();
        phone1.setPhoneId(1L);
        phone1.setPhoneNumber("4444");

        Phone phone2 = new Phone();
        phone2.setPhoneId(1L);
        phone2.setPhoneNumber("4444");
        Set<Phone> phones = new HashSet(Set.of(phone1, phone2));

        PhoneDto phoneDto = PhoneDtoMapper.INSTANCE.toDto(phone);
        Assertions.assertEquals(phone.getPhoneNumber(), phoneDto.getPhoneNumber());
    }

    @Test
    void toEntitys() {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setPhoneNumber("4444");
        Phone phone = PhoneDtoMapper.INSTANCE.toEntity(phoneDto);
        Assertions.assertEquals(phoneDto.getPhoneNumber(), phone.getPhoneNumber());
    }*/
}