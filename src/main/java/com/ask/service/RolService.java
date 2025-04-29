package com.ask.service;

import com.ask.dto.RolRequest;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Rol;
import com.ask.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public Rol crearRol(RolRequest request) {
        Rol rol = new Rol();
        rol.setNombreRol(request.getNombreRol());
        rol.setDescripcionRol(request.getDescripcionRol());
        return rolRepository.save(rol);
    }

    public Rol obtenerRolPorId(UUID id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id.toString()));
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Rol actualizarRol(UUID id, RolRequest request) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id.toString()));

        rol.setNombreRol(request.getNombreRol());
        rol.setDescripcionRol(request.getDescripcionRol());

        return rolRepository.save(rol);
    }

    public void eliminarRol(UUID id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id.toString()));
        rolRepository.delete(rol);
    }
}