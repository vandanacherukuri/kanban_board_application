package com.pm_app.backend.services;

import com.pm_app.backend.dtos.SimplifiedUserDto;
import com.pm_app.backend.mappers.UserMapper;
import com.pm_app.backend.models.User;
import com.pm_app.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class SecurityService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public SecurityService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public static User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findUserByEmail(email).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public SimplifiedUserDto addUser(User userToAdd) {
        return mapper.UserToSimplifiedUserDto(repository.saveAndFlush(userToAdd));
    }
}
