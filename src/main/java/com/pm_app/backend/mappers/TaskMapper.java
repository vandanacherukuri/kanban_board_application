package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.models.Task;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, ToDoMapper.class, ProjectMapper.class}
)
public interface TaskMapper {


    Task SimplifiedDtoToTask(SimplifiedDto source);
    SimplifiedDto TaskToSimplifiedDto(Task source);


}
