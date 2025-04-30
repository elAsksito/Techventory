package com.ask.service;

import com.ask.dto.MovimientoProdRequest;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Movimiento;
import com.ask.model.MovimientoProd;
import com.ask.model.Producto;
import com.ask.repository.MovimientoProdRepository;
import com.ask.repository.MovimientoRepository;
import com.ask.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovimientoProdService {

    private final MovimientoProdRepository movimientoProdRepository;
    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public MovimientoProd guardar(MovimientoProdRequest request) {
        Movimiento movimiento = movimientoRepository.findById(request.getIdMovimiento())
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento", "id", request.getIdMovimiento()));

        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", request.getIdProducto()));

        MovimientoProd movimientoProd = MovimientoProd.builder()
                .cantidad(request.getCantidad())
                .movimiento(movimiento)
                .producto(producto)
                .build();

        return movimientoProdRepository.save(movimientoProd);
    }

    public List<MovimientoProd> guardarTodos(List<MovimientoProdRequest> listaMovimientoProd) {
        List<MovimientoProd> movimientoProds = listaMovimientoProd.stream()
                .map(this::guardar)
                .toList();
        return movimientoProdRepository.saveAll(movimientoProds);
    }

    public MovimientoProd obtenerPorId(String id) {
        return movimientoProdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoProd", "id", id));
    }

    public List<MovimientoProd> listarTodos() {
        return movimientoProdRepository.findAll();
    }

    public MovimientoProd actualizar(String id, MovimientoProdRequest request) {
        MovimientoProd movimientoProd = obtenerPorId(id);

        Movimiento movimiento = movimientoRepository.findById(request.getIdMovimiento())
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento", "id", request.getIdMovimiento()));

        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", request.getIdProducto()));

        movimientoProd.setCantidad(request.getCantidad());
        movimientoProd.setMovimiento(movimiento);
        movimientoProd.setProducto(producto);

        return movimientoProdRepository.save(movimientoProd);
    }

    public void eliminar(String id) {
        MovimientoProd movimientoProd = obtenerPorId(id);
        movimientoProdRepository.delete(movimientoProd);
    }
}