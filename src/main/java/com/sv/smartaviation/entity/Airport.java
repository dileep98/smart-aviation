package com.sv.smartaviation.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "airports", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "code"
        })
})
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 56)
    private String name;

    @NotBlank
    @Size(max = 3)
    private String code;

    @Size(max = 2)
    private String stateCode;

    @Size(max = 2)
    private String countryCode;

    @Size(max = 32)
    private String countryName;

}
