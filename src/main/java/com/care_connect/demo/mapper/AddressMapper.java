package com.care_connect.demo.mapper;

import com.care_connect.demo.domain.Address;
import com.care_connect.demo.request.AddressRequestDto;
import com.care_connect.demo.response.AddressResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private final ModelMapper modelMapper;

    public AddressMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Address toEntity(AddressRequestDto dto) {
        return modelMapper.map(dto, Address.class);
    }

    public AddressResponseDto toResponseDto(Address address) {
        return modelMapper.map(address, AddressResponseDto.class);
    }
}

