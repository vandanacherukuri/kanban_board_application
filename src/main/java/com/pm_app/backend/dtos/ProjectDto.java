package com.pm_app.backend.dtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private List<SimplifiedUserDto> usersAssigned;
    private List<SimplifiedUserDto> tasks;
}
