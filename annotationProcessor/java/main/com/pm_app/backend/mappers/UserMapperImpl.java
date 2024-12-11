package com.pm_app.backend.mappers;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.dtos.SimplifiedUserDto;
import com.pm_app.backend.dtos.UserGetDto;
import com.pm_app.backend.dtos.UserPostDto;
import com.pm_app.backend.models.Project;
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
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public UserPostDto UserToUserPostDto(User source) {
        if ( source == null ) {
            return null;
        }

        UserPostDto userPostDto = new UserPostDto();

        userPostDto.setId( source.getId() );
        userPostDto.setEmail( source.getEmail() );
        userPostDto.setName( source.getName() );
        userPostDto.setSurname( source.getSurname() );
        userPostDto.setPassword( source.getPassword() );

        return userPostDto;
    }

    @Override
    public User UserPostDtoToUser(UserPostDto source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( source.getId() );
        user.setEmail( source.getEmail() );
        user.setPassword( source.getPassword() );
        user.setName( source.getName() );
        user.setSurname( source.getSurname() );

        return user;
    }

    @Override
    public UserGetDto UserToUserGetDto(User source) {
        if ( source == null ) {
            return null;
        }

        UserGetDto userGetDto = new UserGetDto();

        userGetDto.setId( source.getId() );
        userGetDto.setEmail( source.getEmail() );
        userGetDto.setName( source.getName() );
        userGetDto.setSurname( source.getSurname() );
        userGetDto.setProjects( projectListToSimplifiedDtoList( source.getProjects() ) );

        return userGetDto;
    }

    @Override
    public User UserGetDtoToUser(UserGetDto source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( source.getId() );
        user.setEmail( source.getEmail() );
        user.setName( source.getName() );
        user.setSurname( source.getSurname() );
        user.setProjects( simplifiedDtoListToProjectList( source.getProjects() ) );

        return user;
    }

    @Override
    public SimplifiedUserDto UserToSimplifiedUserDto(User source) {
        if ( source == null ) {
            return null;
        }

        SimplifiedUserDto simplifiedUserDto = new SimplifiedUserDto();

        simplifiedUserDto.setId( source.getId() );
        simplifiedUserDto.setEmail( source.getEmail() );
        simplifiedUserDto.setName( source.getName() );
        simplifiedUserDto.setSurname( source.getSurname() );

        return simplifiedUserDto;
    }

    @Override
    public User SimplifiedUserDtoToUser(SimplifiedUserDto source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( source.getId() );
        user.setEmail( source.getEmail() );
        user.setName( source.getName() );
        user.setSurname( source.getSurname() );

        return user;
    }

    protected List<SimplifiedDto> projectListToSimplifiedDtoList(List<Project> list) {
        if ( list == null ) {
            return null;
        }

        List<SimplifiedDto> list1 = new ArrayList<SimplifiedDto>( list.size() );
        for ( Project project : list ) {
            list1.add( projectMapper.ProjectToSimplifiedDto( project ) );
        }

        return list1;
    }

    protected Project simplifiedDtoToProject(SimplifiedDto simplifiedDto) {
        if ( simplifiedDto == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( simplifiedDto.getId() );
        project.setName( simplifiedDto.getName() );
        project.setDescription( simplifiedDto.getDescription() );

        return project;
    }

    protected List<Project> simplifiedDtoListToProjectList(List<SimplifiedDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Project> list1 = new ArrayList<Project>( list.size() );
        for ( SimplifiedDto simplifiedDto : list ) {
            list1.add( simplifiedDtoToProject( simplifiedDto ) );
        }

        return list1;
    }
}
