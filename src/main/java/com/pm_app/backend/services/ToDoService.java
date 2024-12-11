package com.pm_app.backend.services;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.mappers.ToDoMapper;
import com.pm_app.backend.models.Task;
import com.pm_app.backend.models.ToDo;
import com.pm_app.backend.repositories.TaskRepository;
import com.pm_app.backend.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    private final ToDoRepository repository;
    private final ToDoMapper mapper;
    private final TaskRepository HTRepository;

    @Autowired
    public ToDoService(ToDoRepository repository, ToDoMapper mapper, TaskRepository HTRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.HTRepository = HTRepository;
    }

    public List<SimplifiedDto> findAllToDoByTask(Long id) {
        List<ToDo> projects = repository.findByHugeTask(id);
        return projects.stream()
                .map(mapper::ToDoToSimplifiedDto)
                .collect(Collectors.toList());
    }

    public SimplifiedDto getToDoById(Long id) {
        Optional<ToDo> OptionalToDo = repository.findById(id);

        return OptionalToDo.map(mapper::ToDoToSimplifiedDto).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "cannot find project")
        );
    }

    public SimplifiedDto dispatchToDo(SimplifiedDto toDo, Long idHugeTask) {
        if (toDo.getId() != null)
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "id cannot be given");

        Optional<Task> precedentHugeTask = HTRepository.findById(idHugeTask);
        Task fetchedTask = precedentHugeTask.orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "precedent Project is invalid")
        );

        ToDo mappedToDo = mapper.SimplifiedDtoToToDo(toDo);
        mappedToDo.setTask(fetchedTask);

        return mapper.ToDoToSimplifiedDto(repository.saveAndFlush(mappedToDo));
    }

    public SimplifiedDto updateToDo(SimplifiedDto toDo) {
        ToDo fetchedToDo = repository.findById(toDo.getId()).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "id doesn't exists"));
        fetchedToDo.substituteStaticMembers(toDo);

        return mapper.ToDoToSimplifiedDto(repository.saveAndFlush(fetchedToDo));
    }

    public boolean deleteToDo(Long id) {
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException exception) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "id cannot be null");
        }

        return true;
    }
}
