package com.care_connect.demo.response;

import com.care_connect.demo.request.AddressRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CaregiverResponseDto {

    private UUID id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String qualification;
    private BigDecimal hourlyRate;
    private String personalDescription;
    private String phone;
    private Boolean status;
    private Double coverageRadius;

    private AddressRequestDto address;
}

