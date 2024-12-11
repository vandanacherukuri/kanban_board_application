package com.pm_app.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String token;
}
