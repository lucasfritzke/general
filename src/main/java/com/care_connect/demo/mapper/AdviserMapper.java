package com.care_connect.demo.mapper;
import com.care_connect.demo.domain.Adviser;
import com.care_connect.demo.request.AdviserRequestDto;
import com.care_connect.demo.response.AdviserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdviserMapper {

    private final ModelMapper modelMapper;

    public AdviserMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Adviser toEntity(AdviserRequestDto dto) {
        return modelMapper.map(dto, Adviser.class);
    }

    public AdviserResponseDto toResponseDto(Adviser adviser) {
        return modelMapper.map(adviser, AdviserResponseDto.class);
    }
}

