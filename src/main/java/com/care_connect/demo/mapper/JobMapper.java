package com.care_connect.demo.mapper;

import com.care_connect.demo.domain.Job;
import com.care_connect.demo.request.JobRequestDto;
import com.care_connect.demo.response.JobResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    private final ModelMapper modelMapper;

    public JobMapper() {
        this.modelMapper = new ModelMapper();
        // 🔹 Ignora o ID ao mapear DTO -> Entity
    }

    public Job toEntity(JobRequestDto dto) {
        Job job = modelMapper.map(dto, Job.class);
        job.setId(null);
        return job;
    }

    public JobResponseDto toResponseDto(Job job) {
        return modelMapper.map(job, JobResponseDto.class);
    }
}
