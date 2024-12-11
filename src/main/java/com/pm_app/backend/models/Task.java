package com.pm_app.backend.models;


import com.pm_app.backend.dtos.SimplifiedDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tasks")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task implements Substitutable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String state;
    private Integer priority;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "tasksAssigned",
            joinColumns = @JoinColumn(name = "idTasks"),
            inverseJoinColumns = @JoinColumn(name = "idUsers"))
    private List<User> taskAssigned;
    @OneToMany(targetEntity = ToDo.class, mappedBy = "task")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ToDo> toDos;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @Override
    public Task substituteStaticMembers(SimplifiedDto source) {
        if (source.getName() != null) {
            this.setName(source.getName());
        }

        if (source.getDescription() != null) {
            this.setDescription(source.getDescription());
        }

        if (source.getState() != null) {
            this.setState(source.getState());
        }

        if (source.getPriority() != null) {
            this.setPriority(source.getPriority());
        }

        return this;
    }
}
