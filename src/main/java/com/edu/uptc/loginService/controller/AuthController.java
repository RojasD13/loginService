package com.edu.uptc.loginService.controller;

import com.edu.uptc.loginService.dto.EmailRequest;
import com.edu.uptc.loginService.dto.LoginRequest;
import com.edu.uptc.loginService.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return userService.processLoginRequest(req);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LoginRequest req) {
        return userService.processLogout(req);
    }

    @PostMapping("/isLogin")
    public ResponseEntity<?> isLogin(@RequestBody EmailRequest request) {
        return ResponseEntity.ok(userService.isLoggedIn(request.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest req) {
        return userService.registerUser(req);
    }
}
