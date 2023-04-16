package com.sv.smartaviation.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private Integer stopCount;

    @NotBlank
    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @NotBlank
    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;

    @NotBlank
    @Column(nullable = false)
    private String carrierName;

    @NotBlank
    @Column(nullable = false)
    private Double price;
}
