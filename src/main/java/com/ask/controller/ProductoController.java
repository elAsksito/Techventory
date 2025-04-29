package com.ask.controller;

import com.ask.dto.ProductoRequest;
import com.ask.model.Producto;
import com.ask.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoRequest request) {
        Producto nuevoProducto = productoService.guardar(request);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable UUID id) {
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombreProducto) {
        List<Producto> productos = productoService.buscarPorNombre(nombreProducto);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscarPorCategoria")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@RequestParam String nombreCategoria) {
        List<Producto> productos = productoService.buscarPorCategoriaNombre(nombreCategoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscarPorEstado")
    public ResponseEntity<List<Producto>> buscarPorEstado(@RequestParam String nombreEstado) {
        List<Producto> productos = productoService.buscarPorEstadoNombre(nombreEstado);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable UUID id,
            @Valid @RequestBody ProductoRequest request
    ) {
        Producto productoActualizado = productoService.actualizar(id, request);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable UUID id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}