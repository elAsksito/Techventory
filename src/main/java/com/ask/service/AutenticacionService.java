package com.ask.service;

import com.ask.dto.LoginRequest;
import com.ask.dto.RegisterRequest;
import com.ask.exception.BusinessException;
import com.ask.exception.ResourceNotFoundException;
import com.ask.model.Rol;
import com.ask.model.Usuario;
import com.ask.repository.RolRepository;
import com.ask.repository.UsuarioRepository;
import com.ask.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public String login(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByCorreoUsuario(loginRequest.getCorreoUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "correo", loginRequest.getCorreoUsuario()));

        if(!passwordEncoder.matches(loginRequest.getContraseniaUsuario(), usuario.getContraseniaUsuario())){
            throw new BusinessException("Contraseña incorrecta");
        }

        return jwtProvider.generateToken(usuario.getCorreoUsuario(), usuario.getRol().getNombreRol());
    }

    public Usuario register(RegisterRequest registerRequest) {
        if(usuarioRepository.findByCorreoUsuario(registerRequest.getCorreoUsuario()).isPresent()){
            throw new BusinessException("El correo electrónico ya está registrado");
        }

        Usuario usuario = createUsuarioFromRequest(registerRequest);
        usuarioRepository.save(usuario);

        return usuario;
    }

    private Usuario createUsuarioFromRequest(RegisterRequest request) {
        Rol rol = rolRepository.findByNombreRol(request.getRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "nombre", request.getRol()));

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
}