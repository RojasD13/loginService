package com.edu.uptc.loginService.controller;

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

    @GetMapping("/isLogin")
    public ResponseEntity<?> isLogin(@RequestParam String email) {
        return ResponseEntity.ok(userService.isLoggedIn(email));
    }
}
