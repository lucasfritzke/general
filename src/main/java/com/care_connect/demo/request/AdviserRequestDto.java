package com.care_connect.demo.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdviserRequestDto {

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "O CPF não pode estar vazio")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate birthDate;

    @NotNull(message = "O endereço não pode ser nulo")
    @Valid
    private AddressRequestDto address;

    @NotBlank(message = "O e-mail não pode estar vazio")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "A senha não pode estar vazio")
    private String password;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String phone;
}

