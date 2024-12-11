package com.pm_app.backend.services;

import com.pm_app.backend.dtos.SimplifiedUserDto;
import com.pm_app.backend.mappers.UserMapper;
import com.pm_app.backend.models.Project;
import com.pm_app.backend.models.User;
import com.pm_app.backend.repositories.ProjectRepository;
import com.pm_app.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final ProjectRepository projectRepository;

    @Autowired
    public UserService(UserMapper mapper, UserRepository repository, ProjectRepository projectRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public List<SimplifiedUserDto> findAllUsersByTask(Long id) {
        List<User> users = repository.findByTask(id);
        return users.stream()
                .map(mapper::UserToSimplifiedUserDto)
                .collect(Collectors.toList());
    }

    public List<SimplifiedUserDto> findAllUsersByProject(Long id) {
        List<User> users = repository.findByProject(id);
        return users.stream()
                .map(mapper::UserToSimplifiedUserDto)
                .collect(Collectors.toList());
    }

    public SimplifiedUserDto getUserById(Long id) {
        Optional<User> optionalUser = repository.findById(id);

        return optionalUser.map(mapper::UserToSimplifiedUserDto).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "cannot find User")
        );
    }

    public SimplifiedUserDto connectUserToProject(String name, String surname, Long idProject) {
        Optional<User> optionalUser = repository.findUserByNameAndSurname(name, surname);
        User fetchedUser = optionalUser.orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "cannot find User"));
        Optional<Project> optionalProject = projectRepository.findById(idProject);
        Project fetchedProject = optionalProject.orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "cannot find Project"));
        fetchedUser.getProjects().add(fetchedProject);

        return mapper.UserToSimplifiedUserDto(repository.saveAndFlush(fetchedUser));
    }
}
