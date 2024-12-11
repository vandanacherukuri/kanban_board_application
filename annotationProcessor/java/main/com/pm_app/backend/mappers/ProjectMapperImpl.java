package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.ProjectDto;
import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.dtos.SimplifiedUserDto;
import com.pm_app.backend.models.Project;
import com.pm_app.backend.models.Task;
import com.pm_app.backend.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-30T09:29:20+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.2.jar, environment: Java 11.0.20.1 (Ubuntu)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ProjectDto ProjectToProjectDto(Project source) {
        if ( source == null ) {
            return null;
        }

        ProjectDto projectDto = new ProjectDto();

        projectDto.setId( source.getId() );
        projectDto.setName( source.getName() );
        projectDto.setDescription( source.getDescription() );
        projectDto.setUsersAssigned( userListToSimplifiedUserDtoList( source.getUsersAssigned() ) );
        projectDto.setTasks( taskListToSimplifiedUserDtoList( source.getTasks() ) );

        return projectDto;
    }

    @Override
    public Project ProjectDtoToProject(ProjectDto source) {
        if ( source == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( source.getId() );
        project.setName( source.getName() );
        project.setDescription( source.getDescription() );
        project.setUsersAssigned( simplifiedUserDtoListToUserList( source.getUsersAssigned() ) );
        project.setTasks( simplifiedUserDtoListToTaskList( source.getTasks() ) );

        return project;
    }

    @Override
    public SimplifiedDto ProjectToSimplifiedDto(Project source) {
        if ( source == null ) {
            return null;
        }

        SimplifiedDto simplifiedDto = new SimplifiedDto();

        simplifiedDto.setId( source.getId() );
        simplifiedDto.setName( source.getName() );
        simplifiedDto.setDescription( source.getDescription() );

        return simplifiedDto;
    }

    protected List<SimplifiedUserDto> userListToSimplifiedUserDtoList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<SimplifiedUserDto> list1 = new ArrayList<SimplifiedUserDto>( list.size() );
        for ( User user : list ) {
            list1.add( userMapper.UserToSimplifiedUserDto( user ) );
        }

        return list1;
    }

    protected SimplifiedUserDto taskToSimplifiedUserDto(Task task) {
        if ( task == null ) {
            return null;
        }

        SimplifiedUserDto simplifiedUserDto = new SimplifiedUserDto();

        simplifiedUserDto.setId( task.getId() );
        simplifiedUserDto.setName( task.getName() );

        return simplifiedUserDto;
    }

    protected List<SimplifiedUserDto> taskListToSimplifiedUserDtoList(List<Task> list) {
        if ( list == null ) {
            return null;
        }

        List<SimplifiedUserDto> list1 = new ArrayList<SimplifiedUserDto>( list.size() );
        for ( Task task : list ) {
            list1.add( taskToSimplifiedUserDto( task ) );
        }

        return list1;
    }

    protected List<User> simplifiedUserDtoListToUserList(List<SimplifiedUserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( SimplifiedUserDto simplifiedUserDto : list ) {
            list1.add( userMapper.SimplifiedUserDtoToUser( simplifiedUserDto ) );
        }

        return list1;
    }

    protected Task simplifiedUserDtoToTask(SimplifiedUserDto simplifiedUserDto) {
        if ( simplifiedUserDto == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( simplifiedUserDto.getId() );
        task.setName( simplifiedUserDto.getName() );

        return task;
    }

    protected List<Task> simplifiedUserDtoListToTaskList(List<SimplifiedUserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Task> list1 = new ArrayList<Task>( list.size() );
        for ( SimplifiedUserDto simplifiedUserDto : list ) {
            list1.add( simplifiedUserDtoToTask( simplifiedUserDto ) );
        }

        return list1;
    }
}
