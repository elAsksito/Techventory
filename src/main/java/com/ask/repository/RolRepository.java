package com.ask.repository;

import com.ask.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
    Optional<Rol> findByNombreRol(String nombreRol);
}