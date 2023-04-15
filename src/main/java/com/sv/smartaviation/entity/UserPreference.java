package com.sv.smartaviation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "userprefernce")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 10)
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String confirmPassword;

    @NotBlank
    private boolean smsToggle;

    @NotBlank
    private boolean emailToggle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "User",
            joinColumns = @JoinColumn(name = "user_id"))
    private User userId;
}
