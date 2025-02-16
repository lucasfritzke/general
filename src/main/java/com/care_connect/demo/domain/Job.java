package com.care_connect.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(max = 500)
    private String jobDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Integer hoursPerDay;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "adviser_id", nullable = false)
    private Adviser adviser;

    @NotNull
    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private Boolean notify;
}

