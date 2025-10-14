package com.edu.uptc.loginService.service;

import com.edu.uptc.loginService.model.User;
import com.edu.uptc.loginService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Transactional
    public Optional<User> login(String email, String password) {
        return repo.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    user.setLogin(true);
                    return repo.save(user);
                });
    }

    @Transactional
    public Optional<User> logout(String email) {
        return repo.findByEmail(email)
                .map(user -> {
                    user.setLogin(false);
                    repo.save(user);
                    return user;
                });
    }

    public boolean isLoggedIn(String email) {
        return repo.findByEmail(email).map(User::isLogin).orElse(false);
    }
}
