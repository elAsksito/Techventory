package com.ask.controller;

import com.ask.dto.ProductoRequest;
import com.ask.model.Producto;
import com.ask.model.Usuario;
import com.ask.service.ExcelExportService;
import com.ask.service.ProductoService;
import com.ask.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ExcelExportService excelExportService;
    private final UsuarioService usuarioService;

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
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String id) {
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
            @PathVariable String id,
            @Valid @RequestBody ProductoRequest request
    ) {
        Producto productoActualizado = productoService.actualizar(id, request);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/exportar")
    public ResponseEntity<byte[]> exportarProductos(
            @RequestParam String userId,
            @RequestBody List<Producto> productos) throws IOException {

        Usuario usuario = usuarioService.obtenerPorId(userId);

        byte[] excelFile = excelExportService.exportarProductosAExcel(usuario, productos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=productos.xlsx");
        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }
}