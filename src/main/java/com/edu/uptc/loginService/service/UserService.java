package com.edu.uptc.loginService.service;

import com.edu.uptc.loginService.dto.LoginRequest;
import com.edu.uptc.loginService.model.User;
import com.edu.uptc.loginService.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isLoggedIn(String email) {
        return repo.findByEmail(email).map(User::isLogin).orElse(false);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public ResponseEntity<?> processLoginRequest(LoginRequest req) {
        return processRequest(
                () -> this.login(req.getEmail(), req.getPassword()),
                HttpStatus.UNAUTHORIZED,
                "Email o contraseña inválidos");
    }

    @Transactional
    private Optional<User> login(String email, String rawPassword) {
        return repo.findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .map(user -> {
                    user.setLogin(true);
                    return repo.save(user);
                });
    }

    public ResponseEntity<?> processLogout(LoginRequest req) {
        return processRequest(
                () -> this.logout(req.getEmail()),
                HttpStatus.NOT_FOUND,
                "Usuario no encontrado");
    }

    @Transactional
    private Optional<User> logout(String email) {
        return repo.findByEmail(email)
                .map(user -> {
                    user.setLogin(false);
                    repo.save(user);
                    return user;
                });
    }

    private <T> ResponseEntity<?> processRequest(
            Supplier<Optional<T>> action,
            HttpStatus errorStatus,
            String errorMessage) {
        return action.get()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(errorStatus)
                        .body(errorMessage));
    }

}
