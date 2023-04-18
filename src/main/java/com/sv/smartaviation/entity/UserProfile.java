package com.sv.smartaviation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean smsToggle = false;

    @NotBlank
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean emailToggle = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
