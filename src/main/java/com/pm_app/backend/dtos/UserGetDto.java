package com.pm_app.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGetDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private List<SimplifiedDto> hugeTasks;
    private List<SimplifiedDto> projects;
}
