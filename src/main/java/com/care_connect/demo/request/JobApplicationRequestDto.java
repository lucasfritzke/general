package com.care_connect.demo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobApplicationRequestDto {

    @NotNull(message = "O ID da vaga não pode ser nulo")
    private Long jobId;

    @NotNull(message = "O ID do cuidador não pode ser nulo")
    private Long caregiverId;
}
