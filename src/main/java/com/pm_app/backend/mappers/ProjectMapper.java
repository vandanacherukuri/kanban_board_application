package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.ProjectDto;
import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.models.Project;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {TaskMapper.class, ToDoMapper.class, UserMapper.class}
)
public interface ProjectMapper {

    ProjectDto ProjectToProjectDto(Project source);
    Project ProjectDtoToProject(ProjectDto source);

    SimplifiedDto ProjectToSimplifiedDto(Project source);
}
