package com.ask.service;

import com.ask.model.Producto;
import com.ask.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto obtenerPorId(UUID id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto actualizar(UUID id, Producto productoActualizado) {
        Producto producto = obtenerPorId(id);
        producto.setNombreProducto(productoActualizado.getNombreProducto());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setStock(productoActualizado.getStock());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setEstado(productoActualizado.getEstado());
        producto.setCategoria(productoActualizado.getCategoria());
        return productoRepository.save(producto);
    }

    public void eliminar(UUID id) {
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
    }
}