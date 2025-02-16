package com.care_connect.demo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class JobResponseDto {

    private UUID id;
    private String jobDescription;
    private String jobType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer hoursPerDay;
    private String status;
    private Boolean notify;
    private AddressResponseDto address;
    private AdviserResponseDto adviser;

}

