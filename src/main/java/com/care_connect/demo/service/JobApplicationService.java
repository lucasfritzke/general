package com.care_connect.demo.service;

import com.care_connect.demo.domain.JobApplication;
import com.care_connect.demo.mapper.JobApplicationMapper;
import com.care_connect.demo.repository.JobApplicationRepository;
import com.care_connect.demo.request.JobApplicationRequestDto;
import com.care_connect.demo.response.JobApplicationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobApplicationMapper jobApplicationMapper;

    @Transactional
    public JobApplicationResponseDto applyForJob(JobApplicationRequestDto dto) {
        JobApplication jobApplication = jobApplicationMapper.toEntity(dto);
        jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toResponseDto(jobApplication);
    }
}
