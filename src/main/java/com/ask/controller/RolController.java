package com.ask.controller;

import com.ask.dto.RolRequest;
import com.ask.model.Rol;
import com.ask.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody @Valid RolRequest request) {
        Rol rolCreado = rolService.crearRol(request);
        return ResponseEntity.ok(rolCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable String id) {
        return ResponseEntity.ok(rolService.obtenerRolPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable String id, @RequestBody @Valid RolRequest request) {
        return ResponseEntity.ok(rolService.actualizarRol(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable String id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}