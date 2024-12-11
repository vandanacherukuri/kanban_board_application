package com.pm_app.backend.models;

import com.pm_app.backend.dtos.SimplifiedDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "toDos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo implements Substitutable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String state;
    private Integer priority;

    @ManyToOne
    private Task task;

    @Override
    public ToDo substituteStaticMembers(SimplifiedDto source) {
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
