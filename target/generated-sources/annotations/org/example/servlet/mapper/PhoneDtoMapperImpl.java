package org.example.servlet.mapper;

import javax.annotation.processing.Generated;
import org.example.model.Phone;
import org.example.servlet.dto.PhoneDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-06T16:50:21+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class PhoneDtoMapperImpl implements PhoneDtoMapper {

    @Override
    public PhoneDto toDto(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDto phoneDto = new PhoneDto();

        phoneDto.setPhoneNumber( phone.getPhoneNumber() );

        return phoneDto;
    }

    @Override
    public Phone toEntity(PhoneDto phoneDto) {
        if ( phoneDto == null ) {
            return null;
        }

        Phone phone = new Phone();

        phone.setPhoneNumber( phoneDto.getPhoneNumber() );

        return phone;
    }
}
