package com.care_connect.demo.request;
import com.care_connect.demo.domain.JobType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class JobRequestDto {

    @NotBlank(message = "A descrição do trabalho não pode estar vazia")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String jobDescription;

    @NotNull(message = "O tipo do trabalho não pode ser nulo")
    private JobType jobType;

    @NotNull(message = "A data de início não pode ser nula")
    private LocalDateTime startDate;

    @NotNull(message = "A data de término não pode ser nula")
    private LocalDateTime endDate;

    @NotNull(message = "As horas por dia não podem ser nulas")
    private Integer hoursPerDay;

    @NotNull(message = "O anunciante não pode ser nulo")
    private UUID adviserId;

    @NotNull(message = "O endereço não pode ser nulo")
    @Valid
    private AddressRequestDto address;

    private Boolean notify;
}

