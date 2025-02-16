package com.care_connect.demo.service;


import com.care_connect.demo.domain.Address;
import com.care_connect.demo.domain.Adviser;
import com.care_connect.demo.domain.Job;
import com.care_connect.demo.domain.JobStatus;
import com.care_connect.demo.mapper.JobMapper;
import com.care_connect.demo.repository.AdviserRepository;
import com.care_connect.demo.repository.JobRepository;
import com.care_connect.demo.request.JobRequestDto;
import com.care_connect.demo.response.JobResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AdviserRepository adviserRepository;

    @Transactional
    public JobResponseDto postJob(JobRequestDto dto) {
        Job job = jobMapper.toEntity(dto);

        Adviser adviser = adviserRepository.findById(dto.getAdviserId()) .orElseThrow(() -> new RuntimeException("Adviser not found"));
        Address address = addressService.save(job.getAddress());
        job.setAdviser(adviser);
        job.setAddress(address);
        job.setStatus(JobStatus.OPEN);
        jobRepository.save(job);
        return jobMapper.toResponseDto(job);
    }
}

