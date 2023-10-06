package org.example.servlet.mapper;

import org.example.model.Phone;
import org.example.servlet.dto.PhoneDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}