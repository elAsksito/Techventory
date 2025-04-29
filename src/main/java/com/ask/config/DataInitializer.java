package com.ask.config;

import com.ask.model.Rol;
import com.ask.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotFound("ADMIN", "Administrador del sistema");
        createRoleIfNotFound("USER", "Usuario estándar del sistema");
    }

    private void createRoleIfNotFound(String nombre, String descripcion) {
        rolRepository.findByNombreRol(nombre)
                .orElseGet(() -> {
                    Rol rol = new Rol();
                    rol.setNombreRol(nombre);
                    rol.setDescripcionRol(descripcion);
                    return rolRepository.save(rol);
                });
    }
}
