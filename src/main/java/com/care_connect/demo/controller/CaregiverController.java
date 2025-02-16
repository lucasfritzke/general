package com.care_connect.demo.controller;

import com.care_connect.demo.request.CaregiverRequestDto;
import com.care_connect.demo.response.CaregiverResponseDto;
import com.care_connect.demo.service.CaregiverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caregiver")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;

    @PostMapping("/register")
    public ResponseEntity<CaregiverResponseDto> register(@RequestBody @Valid CaregiverRequestDto dto) {
        return ResponseEntity.ok(caregiverService.registerCaregiver(dto));
    }
}

