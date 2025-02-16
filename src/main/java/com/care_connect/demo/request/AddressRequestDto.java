package com.care_connect.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequestDto {

    @NotBlank(message = "A rua não pode estar vazia")
    private String street;

    @NotBlank(message = "O número não pode estar vazio")
    private String number;

    @NotBlank(message = "O bairro não pode estar vazio")
    private String neighborhood;

    @NotBlank(message = "A cidade não pode estar vazia")
    private String city;

    @NotBlank(message = "O estado não pode estar vazio")
    private String state;

    private String country = "Brasil"; // Default

    @NotBlank(message = "O CEP não pode estar vazio")
    private String zipcode;

    private String complement;

}
