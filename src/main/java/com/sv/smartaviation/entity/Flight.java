package com.sv.smartaviation.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Entity
@Data
@Table(name = "flights")
public class Flight extends DateAudit {

    @Id
    private String id;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false)
    private String originName;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false)
    private String originCode;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false)
    private String destinationName;

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false)
    private String destinationCode;

    @NotNull
    @Column(nullable = false)
    private Integer stopCount;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureDateTime;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalDateTime;

    @NotNull
    @Column(nullable = false)
    private String carrierName;

    @NotNull
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String flightNumber;
}
