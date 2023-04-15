package com.sv.smartaviation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "cities")
public class CityEntity {
    @Id
    @Size(max = 3)
    private String code;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String timeZoneId;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(max = 3)
    @Column(nullable = false)
    private String cityCode;

    @NotBlank
    @Size(max = 2)
    @Column(nullable = false)
    private String countryId;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String location;

    @NotBlank
    @Column(nullable = false)
    private Integer elevation;

    @Size(max = 30)
    private String url;

    @Size(max = 4)
    private String icao;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String county;

    @Size(max = 50)
    private String state;
}
