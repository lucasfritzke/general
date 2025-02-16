package com.care_connect.demo.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressResponseDto {
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String complement;
    private Double latitude;
    private Double longitude;
}

