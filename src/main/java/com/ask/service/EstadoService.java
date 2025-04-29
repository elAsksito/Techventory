package com.ask.service;

import com.ask.model.Estado;
import com.ask.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public Estado guardar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado obtenerPorId(UUID id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));
    }

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado actualizar(UUID id, Estado estadoActualizado) {
        Estado estado = obtenerPorId(id);
        estado.setNombreEstado(estadoActualizado.getNombreEstado());
        estado.setDescripcion(estadoActualizado.getDescripcion());
        return estadoRepository.save(estado);
    }

    public void eliminar(UUID id) {
        Estado estado = obtenerPorId(id);
        estadoRepository.delete(estado);
    }
}