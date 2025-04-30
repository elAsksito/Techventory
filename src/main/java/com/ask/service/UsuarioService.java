package com.ask.service;

import com.ask.dto.RegisterRequest;
import com.ask.exception.BusinessException;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Rol;
import com.ask.model.Usuario;
import com.ask.repository.RolRepository;
import com.ask.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario register(RegisterRequest registerRequest) {
        if (usuarioRepository.findByCorreoUsuario(registerRequest.getCorreoUsuario()).isPresent()) {
            throw new BusinessException("El correo electronico ya esta registrado");
        }

        Usuario usuario = createUsuarioFromRequest(registerRequest);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public Usuario obtenerPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }

    public Usuario actualizar(String id, RegisterRequest request) {
        Usuario usuario = obtenerPorId(id);

        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setApellidoUsuario(request.getApellidoUsuario());
        usuario.setDniUsuario(request.getDniUsuario());
        usuario.setTelefonoUsuario(request.getTelefonoUsuario());
        usuario.setCorreoUsuario(request.getCorreoUsuario());
        usuario.setUsernameUsuario(request.getUsernameUsuario());
        usuario.setContraseniaUsuario(passwordEncoder.encode(request.getContraseniaUsuario()));

        Rol rol = getRolByName(request.getRol());
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public void eliminar(String id) {
        Usuario usuario = obtenerPorId(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario createUsuarioFromRequest(RegisterRequest request) {
        Rol rol = getRolByName(request.getRol());

        return Usuario.builder()
                .nombreUsuario(request.getNombreUsuario())
                .apellidoUsuario(request.getApellidoUsuario())
                .dniUsuario(request.getDniUsuario())
                .telefonoUsuario(request.getTelefonoUsuario())
                .correoUsuario(request.getCorreoUsuario())
                .usernameUsuario(request.getUsernameUsuario())
                .contraseniaUsuario(passwordEncoder.encode(request.getContraseniaUsuario()))
                .rol(rol)
                .build();
    }

    private Rol getRolByName(String rolName) {
        return rolRepository.findByNombreRol(rolName)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "nombre", rolName));
    }
}