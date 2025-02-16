package com.care_connect.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "adviser")
public class Adviser {

    @Id
    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Size(max = 20)
    private String phone;

    @OneToMany(mappedBy = "adviser")
    private List<Job> jobs;
}

