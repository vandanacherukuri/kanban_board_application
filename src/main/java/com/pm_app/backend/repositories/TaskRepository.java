package com.pm_app.backend.repositories;

import com.pm_app.backend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT t FROM Task t inner join fetch t.project as p where p.id = ?1")
    List<Task> findByIdProject(Long idProject);

    @Query(value = "SELECT t FROM Task t inner join fetch t.taskAssigned as a where a.id = ?1")
    List<Task> findByIdUser(Long idUser);
}
