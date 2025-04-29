package com.ask.service;

import com.ask.model.MovimientoProd;
import com.ask.repository.MovimientoProdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovimientoProdService {

    private final MovimientoProdRepository movimientoProdRepository;

    public MovimientoProd guardar(MovimientoProd movimientoProd) {
        return movimientoProdRepository.save(movimientoProd);
    }

    public List<MovimientoProd> guardarTodos(List<MovimientoProd> listaMovimientoProd) {
        return movimientoProdRepository.saveAll(listaMovimientoProd);
    }

    public MovimientoProd obtenerPorId(UUID id) {
        return movimientoProdRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento de producto no encontrado"));
    }

    public List<MovimientoProd> listarTodos() {
        return movimientoProdRepository.findAll();
    }

    public MovimientoProd actualizar(UUID id, MovimientoProd movimientoProdActualizado) {
        MovimientoProd movimientoProd = obtenerPorId(id);
        movimientoProd.setProducto(movimientoProdActualizado.getProducto());
        movimientoProd.setMovimiento(movimientoProdActualizado.getMovimiento());
        movimientoProd.setCantidad(movimientoProdActualizado.getCantidad());
        return movimientoProdRepository.save(movimientoProd);
    }

    public void eliminar(UUID id) {
        MovimientoProd movimientoProd = obtenerPorId(id);
        movimientoProdRepository.delete(movimientoProd);
    }
}
