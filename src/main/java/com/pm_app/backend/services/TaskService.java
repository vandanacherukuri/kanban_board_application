package com.pm_app.backend.services;


import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.mappers.TaskMapper;
import com.pm_app.backend.models.Project;
import com.pm_app.backend.models.Task;
import com.pm_app.backend.repositories.ProjectRepository;
import com.pm_app.backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    public List<SimplifiedDto> getTasksByIdUser(Long id) {
        List<Task> projects = taskRepository.findByIdUser(id);
        return projects.stream()
                .map(taskMapper::TaskToSimplifiedDto)
                .collect(Collectors.toList());
    }

    public List<SimplifiedDto> getTasksByIdProject(Long id) {
        List<Task> projects = taskRepository.findByIdProject(id);
        return projects.stream()
                .map(taskMapper::TaskToSimplifiedDto)
                .collect(Collectors.toList());
    }

    public SimplifiedDto getTaskById(Long id) {
        Optional<Task> OptionalProject = taskRepository.findById(id);

        return OptionalProject.map(taskMapper::TaskToSimplifiedDto).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "cannot find task")
        );
    }

    public SimplifiedDto dispatchTask(SimplifiedDto task, Long idProject) {
        Project predecentProject = this.getProjectById(idProject);

        Task taskEntity = taskMapper.SimplifiedDtoToTask(task);
        taskEntity.setProject(predecentProject);

        return taskMapper.TaskToSimplifiedDto(taskRepository.saveAndFlush(taskEntity));
    }

    public SimplifiedDto updateTask(SimplifiedDto task) {
        if (task.getId() == null || !taskRepository.existsById(task.getId()))
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "id doesn't exists");

        Task fetchedTask = taskRepository.findById(task.getId()).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "id doesn't exists"));
        fetchedTask.substituteStaticMembers(task);

        return taskMapper.TaskToSimplifiedDto(taskRepository.saveAndFlush(fetchedTask));
    }

    private Project getProjectById(Long id) {
        Optional<Project> precedentProject = projectRepository.findById(id);

        return precedentProject.orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "precedent Project is invalid")
        );
    }

    public boolean deleteTask(SimplifiedDto task) {
        try {
            taskRepository.deleteById(task.getId());
        } catch (IllegalArgumentException exception) {

            throw new HttpClientErrorException(HttpStatus.I_AM_A_TEAPOT, "id cannot be null");
        }

        return true;
    }
}
