package com.care_connect.demo.controller;

import com.care_connect.demo.request.JobRequestDto;
import com.care_connect.demo.response.JobResponseDto;
import com.care_connect.demo.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<JobResponseDto> registerJob(@RequestBody @Valid JobRequestDto dto) {
        return ResponseEntity.ok(jobService.postJob(dto));
    }
}

