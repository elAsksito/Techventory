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

    public Estado obtenerPorId(String id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado", "id", id));
    }

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado actualizar(String id, EstadoRequest request) {
        Estado estado = obtenerPorId(id);
        estado.setNombreEstado(request.getNombreEstado());
        estado.setDescripcion(request.getDescripcion());
        return estadoRepository.save(estado);
    }

    public void eliminar(String id) {
        Estado estado = obtenerPorId(id);
        estadoRepository.delete(estado);
    }
}