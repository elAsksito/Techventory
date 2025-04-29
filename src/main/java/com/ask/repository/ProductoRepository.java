package com.ask.repository;

import com.ask.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);
    List<Producto> findByCategoriaNombreCategoriaContainingIgnoreCase(String nombreCategoria);
    List<Producto> findByEstadoNombreEstadoContainingIgnoreCase(String nombreEstado);
}