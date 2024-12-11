package com.pm_app.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Projects")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "projectAssigned",
            joinColumns = @JoinColumn(name = "idProjects"),
            inverseJoinColumns = @JoinColumn(name = "idUsers"))
    private List<User> usersAssigned;
    @OneToMany(targetEntity = Task.class, mappedBy = "project")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Task> tasks;

}
