package com.sv.smartaviation.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String timeZoneId;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 3)
    private String cityCode;

    @NotBlank
    @Size(max = 2)
    private String countryId;

    @NotBlank
    @Size(max = 50)
    private String location;

    @NotBlank
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
