package com.edu.uptc.loginService.controller;

import com.edu.uptc.loginService.dto.EmailRequest;
import com.edu.uptc.loginService.dto.LoginRequest;
import com.edu.uptc.loginService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }
    }

    @BeforeEach
    void resetMocks() {
        clearInvocations(userService);
    }

    @Test
    @DisplayName("POST /login debe delegar correctamente en userService.processLoginRequest")
    void testLoginEndpoint() throws Exception {
        LoginRequest loginReq = new LoginRequest();
        loginReq.setEmail("test@example.com");
        loginReq.setPassword("1234");

        when(userService.processLoginRequest(any(LoginRequest.class)))
                .thenAnswer(inv -> ResponseEntity.ok("Login exitoso"));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginReq)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login exitoso"));

        verify(userService).processLoginRequest(any(LoginRequest.class));
    }

    @Test
    @DisplayName("POST /logout debe delegar correctamente en userService.processLogout")
    void testLogoutEndpoint() throws Exception {
        LoginRequest logoutReq = new LoginRequest();
        logoutReq.setEmail("test@example.com");
        logoutReq.setPassword("1234");

        when(userService.processLogout(any(LoginRequest.class)))
                .thenAnswer(inv -> ResponseEntity.ok("Logout exitoso"));

        mockMvc.perform(post("/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logoutReq)))
                .andExpect(status().isOk())
                .andExpect(content().string("Logout exitoso"));

        verify(userService).processLogout(any(LoginRequest.class));
    }

    @Test
    @DisplayName("POST /isLogin debe retornar true si el usuario está logueado")
    void testIsLoginTrue() throws Exception {
        EmailRequest req = new EmailRequest();
        req.setEmail("test@example.com");

        when(userService.isLoggedIn("test@example.com")).thenReturn(true);

        mockMvc.perform(post("/isLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService).isLoggedIn("test@example.com");
    }

    @Test
    @DisplayName("POST /isLogin debe retornar false si el usuario no está logueado")
    void testIsLoginFalse() throws Exception {
        EmailRequest req = new EmailRequest();
        req.setEmail("test@example.com");
        when(userService.isLoggedIn("test@example.com")).thenReturn(false);

        mockMvc.perform(post("/isLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(userService, times(1)).isLoggedIn("test@example.com");
    }
}
