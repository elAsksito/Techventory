package com.ask.service;

import com.ask.dto.MovimientoRequest;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Movimiento;
import com.ask.model.Usuario;
import com.ask.repository.MovimientoRepository;
import com.ask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final UsuarioRepository usuarioRepository;

    public Movimiento guardar(MovimientoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", request.getIdUsuario()));

        Movimiento movimiento = Movimiento.builder()
                .usuario(usuario)
                .tipoMovimiento(request.getTipoMovimiento())
                .fechaMovimiento(request.getFechaMovimiento())
                .observaciones(request.getObservaciones())
                .build();

        return movimientoRepository.save(movimiento);
    }

    public Movimiento obtenerPorId(UUID id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento", "id", id));
    }

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAll();
    }

    public Movimiento actualizar(UUID id, MovimientoRequest request) {
        Movimiento movimiento = obtenerPorId(id);
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", request.getIdUsuario()));

        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setFechaMovimiento(request.getFechaMovimiento());
        movimiento.setObservaciones(request.getObservaciones());
        movimiento.setUsuario(usuario);

        return movimientoRepository.save(movimiento);
    }

    public void eliminar(UUID id) {
        Movimiento movimiento = obtenerPorId(id);
        movimientoRepository.delete(movimiento);
    }
}