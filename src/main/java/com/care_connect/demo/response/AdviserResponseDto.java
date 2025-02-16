package com.care_connect.demo.response;

import com.care_connect.demo.request.AddressRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AdviserResponseDto {

    private UUID id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String phone;
    private AddressRequestDto address;
}

