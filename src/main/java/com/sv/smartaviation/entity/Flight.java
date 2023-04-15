package com.sv.smartaviation.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "flights")
public class Flight extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
