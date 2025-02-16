package com.care_connect.demo.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class JobApplicationResponseDto {

    private UUID id;
    private JobResponseDto job;
    private CaregiverResponseDto caregiver;
    private String status;
}

