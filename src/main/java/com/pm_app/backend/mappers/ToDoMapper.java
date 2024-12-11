package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.models.ToDo;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {TaskMapper.class, UserMapper.class, ProjectMapper.class}
)
public interface ToDoMapper {

    SimplifiedDto ToDoToSimplifiedDto(ToDo source);
    ToDo SimplifiedDtoToToDo(SimplifiedDto source);
}
