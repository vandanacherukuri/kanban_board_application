package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.models.Task;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-30T09:29:20+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.20.1 (Ubuntu)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task SimplifiedDtoToTask(SimplifiedDto source) {
        if ( source == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( source.getId() );
        task.setName( source.getName() );
        task.setDescription( source.getDescription() );
        task.setState( source.getState() );
        task.setPriority( source.getPriority() );

        return task;
    }

    @Override
    public SimplifiedDto TaskToSimplifiedDto(Task source) {
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
}
