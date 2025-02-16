package com.care_connect.demo.controller;

import com.care_connect.demo.request.AdviserRequestDto;
import com.care_connect.demo.response.AdviserResponseDto;
import com.care_connect.demo.service.AdviserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adviser")
public class AdviserController {

    @Autowired
    private AdviserService adviserService;

    @PostMapping("/register")
    public ResponseEntity<AdviserResponseDto> register(@RequestBody @Valid AdviserRequestDto dto) {
        return ResponseEntity.ok(adviserService.registerAdviser(dto));
    }
}

