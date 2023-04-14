package com.sv.smartaviation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;


@Entity
@Data
@Table(name = "airports")
@Indexed
public class Airport {
    @Id
    @Size(max = 3)
    private String code;

    @Size(max = 30)
    @NotBlank
    private String timeZoneId;

    @Size(max = 70)
    @NotBlank
    @FullTextField(analyzer = "custom_lowercase")
    @KeywordField(name = "name_sort", sortable = Sortable.YES)
    private String name;

    @Size(max = 3)
    @NotBlank
    @FullTextField(analyzer = "custom_lowercase")
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
    @FullTextField(analyzer = "custom_lowercase")
    private String city;

    @Size(max = 50)
    @FullTextField(analyzer = "custom_lowercase")
    private String county;

    @Size(max = 70)
    @FullTextField(analyzer = "custom_lowercase")
    private String state;

}
