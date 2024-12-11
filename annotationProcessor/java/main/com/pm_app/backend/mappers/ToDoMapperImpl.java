package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.models.ToDo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-30T09:29:20+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.20.1 (Ubuntu)"
)
@Component
public class ToDoMapperImpl implements ToDoMapper {

    @Override
    public SimplifiedDto ToDoToSimplifiedDto(ToDo source) {
        if ( source == null ) {
            return null;
        }

        SimplifiedDto simplifiedDto = new SimplifiedDto();

        simplifiedDto.setId( source.getId() );
        simplifiedDto.setName( source.getName() );
        simplifiedDto.setDescription( source.getDescription() );
        simplifiedDto.setState( source.getState() );
        simplifiedDto.setPriority( source.getPriority() );

        return simplifiedDto;
    }

    @Override
    public ToDo SimplifiedDtoToToDo(SimplifiedDto source) {
        if ( source == null ) {
            return null;
        }

        ToDo toDo = new ToDo();

        toDo.setId( source.getId() );
        toDo.setName( source.getName() );
        toDo.setDescription( source.getDescription() );
        toDo.setState( source.getState() );
        toDo.setPriority( source.getPriority() );

        return toDo;
    }
}
