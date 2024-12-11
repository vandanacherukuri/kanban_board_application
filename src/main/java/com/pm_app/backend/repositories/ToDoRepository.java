package com.pm_app.backend.repositories;

import com.pm_app.backend.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query(value = "SELECT td FROM ToDo td inner join fetch td.task as t where t.id = ?1")
    List<ToDo> findByHugeTask(Long idTask);
}
