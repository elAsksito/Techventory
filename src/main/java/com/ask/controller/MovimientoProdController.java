package com.ask.controller;

import com.ask.dto.MovimientoProdRequest;
import com.ask.model.MovimientoProd;
import com.ask.service.MovimientoProdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos-prod")
@RequiredArgsConstructor
public class MovimientoProdController {

    private final MovimientoProdService movimientoProdService;

    @PostMapping
    public ResponseEntity<MovimientoProd> crearMovimientoProd(@Valid @RequestBody MovimientoProdRequest request) {
        MovimientoProd nuevoMovimientoProd = movimientoProdService.guardar(request);
        return ResponseEntity.status(201).body(nuevoMovimientoProd);
    }

    @PostMapping("/todos")
    public ResponseEntity<List<MovimientoProd>> crearMovimientosProd(@Valid @RequestBody List<MovimientoProdRequest> requestList) {
        List<MovimientoProd> movimientosProd = movimientoProdService.guardarTodos(requestList);
        return ResponseEntity.status(201).body(movimientosProd);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoProd>> listarMovimientoProd() {
        List<MovimientoProd> movimientosProd = movimientoProdService.listarTodos();
        return ResponseEntity.ok(movimientosProd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoProd> obtenerMovimientoProd(@PathVariable UUID id) {
        MovimientoProd movimientoProd = movimientoProdService.obtenerPorId(id);
        return ResponseEntity.ok(movimientoProd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoProd> actualizarMovimientoProd(
            @PathVariable UUID id,
            @Valid @RequestBody MovimientoProdRequest request
    ) {
        MovimientoProd movimientoProdActualizado = movimientoProdService.actualizar(id, request);
        return ResponseEntity.ok(movimientoProdActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimientoProd(@PathVariable UUID id) {
        movimientoProdService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}