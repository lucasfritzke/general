package com.care_connect.demo.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CaregiverRequestDto {

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

        @NotBlank(message = "A qualificação não pode estar vazia")
        private String qualification;

        @NotNull(message = "O valor por hora não pode ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor por hora deve ser positivo")
        private BigDecimal hourlyRate;

        @Size(max = 300, message = "A descrição pessoal deve ter no máximo 300 caracteres")
        private String personalDescription;

        @NotBlank(message = "O e-mail não pode estar vazio")
        @Email(message = "E-mail inválido")
        private String email;

        @NotBlank(message = "A senha não pode estar vazio")
        private String password;

        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
        private String phone;

        private Double coverageRadius;
    }

