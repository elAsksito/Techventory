package com.ask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    private String correoUsuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contraseniaUsuario;
}