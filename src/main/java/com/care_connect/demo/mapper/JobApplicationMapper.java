package com.care_connect.demo.mapper;

import com.care_connect.demo.domain.JobApplication;
import com.care_connect.demo.request.JobApplicationRequestDto;
import com.care_connect.demo.response.JobApplicationResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationMapper {

    private final ModelMapper modelMapper;

    public JobApplicationMapper() {
        this.modelMapper = new ModelMapper();
    }

    public JobApplication toEntity(JobApplicationRequestDto dto) {
        return modelMapper.map(dto, JobApplication.class);
    }

    public JobApplicationResponseDto toResponseDto(JobApplication jobApplication) {
        return modelMapper.map(jobApplication, JobApplicationResponseDto.class);
    }
}

