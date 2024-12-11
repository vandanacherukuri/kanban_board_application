package com.pm_app.backend.controllers;

import com.pm_app.backend.dtos.SimplifiedDto;
import com.pm_app.backend.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ToDoController extends AbstractController {

    private final ToDoService service;

    @Autowired
    public ToDoController(ToDoService service) {
        super(ToDoController.class);
        this.service = service;
    }

    @PostMapping("/task/{id}/todo")
    private ResponseEntity<SimplifiedDto> createToDo(@RequestBody SimplifiedDto toDo, @PathVariable Long id) {
        logger.info(this.getClass().toString() + ":createToDo:" + getTime());
        SimplifiedDto savedProject = service.dispatchToDo(toDo, id);

        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/todo")
    private ResponseEntity<SimplifiedDto> updateToDo(@RequestBody SimplifiedDto toDo) {
        logger.info(this.getClass().toString() + ":updateToDo:" + getTime());
        SimplifiedDto savedToDo = service.updateToDo(toDo);

        return ResponseEntity.ok(savedToDo);
    }

    @DeleteMapping("/todo/{id}")
    private ResponseEntity<Long> deleteToDo(@PathVariable Long id) {
        logger.info(this.getClass().toString() + ":deleteToDo:" + getTime());

        return service.deleteToDo(id) ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/task/{id}/todo")
    private ResponseEntity<List<SimplifiedDto>> getToDoByTask(@PathVariable Long id) {
        logger.info(this.getClass().toString() + ":getToDoByTask:" + getTime());
        List<SimplifiedDto> toDoList = service.findAllToDoByTask(id);

        if (toDoList.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(toDoList);
    }

    @GetMapping("/todo/{id}")
    private ResponseEntity<SimplifiedDto> getToDoById(@PathVariable Long id) {
        logger.info(this.getClass().toString() + ":getToDoById:" + getTime());

        return ResponseEntity.ok(service.getToDoById(id));
    }

}
