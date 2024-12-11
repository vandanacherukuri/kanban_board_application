package com.pm_app.backend.repositories;

import com.pm_app.backend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p FROM Project p inner join fetch p.usersAssigned as pa where pa.id = ?1")
    List<Project> findByIdUser(Long idUsers);
}
