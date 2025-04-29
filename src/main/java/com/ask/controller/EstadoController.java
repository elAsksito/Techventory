package com.ask.controller;

import com.ask.dto.EstadoRequest;
import com.ask.model.Estado;
import com.ask.service.EstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @PostMapping
    public ResponseEntity<Estado> crearEstado(@Valid @RequestBody EstadoRequest request) {
        Estado nuevoEstado = estadoService.guardar(request);
        return ResponseEntity.status(201).body(nuevoEstado);
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> estados = estadoService.listarTodos();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerEstado(@PathVariable UUID id) {
        Estado estado = estadoService.obtenerPorId(id);
        return ResponseEntity.ok(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizarEstado(
            @PathVariable UUID id,
            @Valid @RequestBody EstadoRequest request
    ) {
        Estado estadoActualizado = estadoService.actualizar(id, request);
        return ResponseEntity.ok(estadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable UUID id) {
        estadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}