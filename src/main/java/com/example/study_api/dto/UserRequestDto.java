package com.example.study_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    @NotEmpty
    private String username;

    @Email
    private String email;

    @NotEmpty
    private String password;
}
