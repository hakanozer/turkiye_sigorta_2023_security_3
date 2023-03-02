package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(unique = true, length = 100)
    @Length(min = 4, max = 100)
    @NotEmpty
    @Email
    @NotNull
    private String email;

    @Column(length = 500)
    @NotEmpty
    @NotNull
    private String password;
    private String remember;

}
