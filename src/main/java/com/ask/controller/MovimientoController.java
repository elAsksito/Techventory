package com.ask.controller;

import com.ask.dto.MovimientoRequest;
import com.ask.model.Movimiento;
import com.ask.service.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> crearMovimiento(@Valid @RequestBody MovimientoRequest request) {
        Movimiento nuevoMovimiento = movimientoService.guardar(request);
        return ResponseEntity.status(201).body(nuevoMovimiento);
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> listarMovimientos() {
        List<Movimiento> movimientos = movimientoService.listarTodos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimiento(@PathVariable UUID id) {
        Movimiento movimiento = movimientoService.obtenerPorId(id);
        return ResponseEntity.ok(movimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(
            @PathVariable UUID id,
            @Valid @RequestBody MovimientoRequest request
    ) {
        Movimiento movimientoActualizado = movimientoService.actualizar(id, request);
        return ResponseEntity.ok(movimientoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable UUID id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}