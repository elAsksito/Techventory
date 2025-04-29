package com.ask.service;

import com.ask.model.Movimiento;
import com.ask.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public Movimiento guardar(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Movimiento obtenerPorId(UUID id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
    }

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAll();
    }

    public Movimiento actualizar(UUID id, Movimiento movimientoActualizado) {
        Movimiento movimiento = obtenerPorId(id);
        movimiento.setTipoMovimiento(movimientoActualizado.getTipoMovimiento());
        movimiento.setFechaMovimiento(movimientoActualizado.getFechaMovimiento());
        movimiento.setObservaciones(movimientoActualizado.getObservaciones());
        return movimientoRepository.save(movimiento);
    }

    public void eliminar(UUID id) {
        Movimiento movimiento = obtenerPorId(id);
        movimientoRepository.delete(movimiento);
    }
}