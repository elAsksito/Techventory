package com.ask.service;

import com.ask.dto.RolRequest;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Rol;
import com.ask.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Rol obtenerRolPorId(String id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id));
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Rol actualizarRol(String id, RolRequest request) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id));

        rol.setNombreRol(request.getNombreRol());
        rol.setDescripcionRol(request.getDescripcionRol());

        return rolRepository.save(rol);
    }

    public void eliminarRol(String id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "ID", id));
        rolRepository.delete(rol);
    }
}