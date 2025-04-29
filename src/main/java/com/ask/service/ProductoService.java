package com.ask.service;

import com.ask.dto.ProductoRequest;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Producto;
import com.ask.model.Categoria;
import com.ask.model.Estado;
import com.ask.repository.ProductoRepository;
import com.ask.repository.CategoriaRepository;
import com.ask.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final EstadoRepository estadoRepository;

    public Producto guardar(ProductoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", request.getIdCategoria()));
        Estado estado = estadoRepository.findById(request.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", request.getIdEstado()));

        Producto producto = Producto.builder()
                .nombreProducto(request.getNombreProducto())
                .descripcion(request.getDescripcion())
                .stock(request.getStock())
                .precio(request.getPrecio())
                .sku(request.getSku())
                .codigoBarras(request.getCodigoBarras())
                .categoria(categoria)
                .estado(estado)
                .build();

        return productoRepository.save(producto);
    }

    public Producto obtenerPorId(UUID id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> buscarPorNombre(String nombreProducto) {
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombreProducto);
    }

    public List<Producto> buscarPorCategoriaNombre(String nombreCategoria) {
        return productoRepository.findByCategoriaNombreCategoriaContainingIgnoreCase(nombreCategoria);
    }

    public List<Producto> buscarPorEstadoNombre(String nombreEstado) {
        return productoRepository.findByEstadoNombreEstadoContainingIgnoreCase(nombreEstado);
    }

    public Producto actualizar(UUID id, ProductoRequest request) {
        Producto producto = obtenerPorId(id);
        Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", request.getIdCategoria()));
        Estado estado = estadoRepository.findById(request.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", request.getIdEstado()));

        producto.setNombreProducto(request.getNombreProducto());
        producto.setDescripcion(request.getDescripcion());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());
        producto.setSku(request.getSku());
        producto.setCodigoBarras(request.getCodigoBarras());
        producto.setCategoria(categoria);
        producto.setEstado(estado);

        return productoRepository.save(producto);
    }

    public void eliminar(UUID id) {
        Producto producto = obtenerPorId(id);
        productoRepository.delete(producto);
    }
}