package com.care_connect.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "caregiver")
public class Caregiver {

    @Id
    private UUID id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "O CPF não pode estar vazio")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotBlank(message = "A qualificação não pode estar vazia")
    private String qualification;

    @NotNull(message = "O valor por hora não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor por hora deve ser positivo")
    @Column(name = "hourly_rate", precision = 10, scale = 2, nullable = false)
    private BigDecimal hourlyRate;

    @Size(max = 300, message = "A descrição pessoal deve ter no máximo 300 caracteres")
    @Column(name = "personal_description")
    private String personalDescription;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String phone;

    @Column(nullable = false)
    private Boolean status = true; // Ativo por padrão

    private Double coverageRadius;

    @OneToMany(mappedBy = "caregiver")
    private List<JobApplication> jobApplications;
}

