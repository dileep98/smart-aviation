package com.sv.smartaviation.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "airports")
public class Airport {
    @Id
    @Size(max = 3)
    private String code;

    @Size(max = 30)
    @NotBlank
    private String timeZoneId;

    @Size(max = 70)
    @NotBlank
    private String name;

    @Size(max = 3)
    @NotBlank
    private String cityCode;

    @Size(max = 2)
    @NotBlank
    private String countryId;

    @Size(max = 50)
    @NotBlank
    private String location;

    @NotBlank
    private Integer elevation;

    @Size(max = 225)
    private String url;

    @Size(max = 4)
    private String icao;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String county;

    @Size(max = 70)
    private String state;

}
