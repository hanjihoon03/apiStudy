package com.example.study_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String username;

    @Email
    @Column(nullable = false)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Product> products = new ArrayList<>();
}
