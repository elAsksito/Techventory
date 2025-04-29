package com.ask.service;

import com.ask.dto.EstadoRequest;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Estado;
import com.ask.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public Estado guardar(EstadoRequest request) {
        Estado estado = new Estado();
        estado.setNombreEstado(request.getNombreEstado());
        estado.setDescripcion(request.getDescripcion());
        return estadoRepository.save(estado);
    }

    public Estado obtenerPorId(UUID id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", id));
    }

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado actualizar(UUID id, EstadoRequest request) {
        Estado estado = obtenerPorId(id);
        estado.setNombreEstado(request.getNombreEstado());
        estado.setDescripcion(request.getDescripcion());
        return estadoRepository.save(estado);
    }

    public void eliminar(UUID id) {
        Estado estado = obtenerPorId(id);
        estadoRepository.delete(estado);
    }
}