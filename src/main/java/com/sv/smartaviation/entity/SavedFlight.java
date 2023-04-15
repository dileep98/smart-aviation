package com.sv.smartaviation.entity;

import lombok.Data;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "savedflights")
@Indexed
public class SavedFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String originName;

    @NotBlank
    @Size(max = 40)
    private String originCode;

    @NotBlank
    @Size(max = 40)
    private String destinationName;

    @NotBlank
    @Size(max = 40)
    private String destinationCode;

    @NotBlank
    @Size(max = 15)
    private Integer stopCount;

    @NotBlank
    private LocalDateTime departureDateTime;

    @NotBlank
    private LocalDateTime arrivalDateTime;

    @NotBlank
    private String carrierName;

    @NotBlank
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "User",
            joinColumns = @JoinColumn(name = "user_id"))
    private User userId;



}
