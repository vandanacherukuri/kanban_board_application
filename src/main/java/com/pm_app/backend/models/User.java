package com.pm_app.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    @Enumerated(EnumType.ORDINAL)
    private PermissionEnum permission = PermissionEnum.USER;

    @ManyToMany
    @JoinTable(
            name = "projectAssigned",
            joinColumns = @JoinColumn(name = "idUsers"),
            inverseJoinColumns = @JoinColumn(name = "idProjects"))
    private List<Project> projects;
    @ManyToMany
    @JoinTable(
            name = "tasksAssigned",
            joinColumns = @JoinColumn(name = "idUsers"),
            inverseJoinColumns = @JoinColumn(name = "idTasks"))
    private List<Task> tasks;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.getPermission() == PermissionEnum.USER) {
            authorities.add(new SimpleGrantedAuthority(PermissionEnum.USER.name()));
            return authorities;
        }
        authorities.add(new SimpleGrantedAuthority(PermissionEnum.PROJECT_MANAGER.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
