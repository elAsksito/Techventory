package com.ask.controller;

import com.ask.dto.LoginRequest;
import com.ask.dto.RegisterRequest;
import com.ask.model.Usuario;
import com.ask.service.AutenticacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AutenticacionService autenticacionService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(autenticacionService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(autenticacionService.login(request));
    }
}