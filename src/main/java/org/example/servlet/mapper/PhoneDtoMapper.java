package org.example.servlet.mapper;

import org.example.model.Phone;
import org.example.servlet.dto.PhoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneDtoMapper {
    PhoneDtoMapper INSTANCE = Mappers.getMapper(PhoneDtoMapper.class);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    PhoneDto toDto(Phone phone);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    Phone toEntity(PhoneDto phoneDto);

}
