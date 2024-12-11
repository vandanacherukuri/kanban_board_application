package com.pm_app.backend.controllers;

import com.pm_app.backend.configs.TokenUtil;
import com.pm_app.backend.dtos.UserPostDto;
import com.pm_app.backend.mappers.UserMapper;
import com.pm_app.backend.models.PermissionEnum;
import com.pm_app.backend.models.User;
import com.pm_app.backend.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class SecurityController extends AbstractController {
    private final TokenUtil tokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final SecurityService service;

    @Autowired
    public SecurityController(TokenUtil tokenUtil, AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder, SecurityService securityService, UserMapper userMapper)
    {
        super(SecurityController.class);

        this.tokenUtil = tokenUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.mapper = userMapper;
        this.service = securityService;
    }

    @PostMapping("/login")
    private ResponseEntity<UserPostDto> login(@RequestBody UserPostDto user) {
        UserPostDto responseBody = this.tryLog(user);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/register")
    private ResponseEntity<Boolean> register(@RequestBody UserPostDto user) {
        if (this.tryRegister(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public UserPostDto tryLog(UserPostDto loginPayload) {
        authenticate(loginPayload.getEmail(), loginPayload.getPassword());
        User user = service.loadUserByUsername(loginPayload.getEmail());
        UserPostDto loginResponseBody = mapper.UserToUserPostDto(user);
        loginResponseBody.setPassword(null);
        String token = tokenUtil.generateToken(user);
        loginResponseBody.setToken(token);

        return loginResponseBody;
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE, "bad login data");
        }
    }

    public boolean tryRegister(@RequestBody UserPostDto userPayload) {

        userPayload.setPassword(passwordEncoder.encode(userPayload.getPassword()));
        User entity = mapper.UserPostDtoToUser(userPayload);
        entity.setPermission(PermissionEnum.USER);

        return service.addUser(entity) != null;
    }
}
