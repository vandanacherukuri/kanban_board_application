package com.pm_app.backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SimplifiedDto {
    private Long id;
    private String name;
    private String description;
    private String state;
    private Integer priority;
}
