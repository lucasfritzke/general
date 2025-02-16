package com.care_connect.demo.mapper;
import com.care_connect.demo.domain.Caregiver;
import com.care_connect.demo.request.CaregiverRequestDto;
import com.care_connect.demo.response.CaregiverResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CaregiverMapper {

    private final ModelMapper modelMapper;

    public CaregiverMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Caregiver toEntity(CaregiverRequestDto dto) {
        return modelMapper.map(dto, Caregiver.class);
    }

    public CaregiverResponseDto toResponseDto(Caregiver caregiver) {
        return modelMapper.map(caregiver, CaregiverResponseDto.class);
    }
}

