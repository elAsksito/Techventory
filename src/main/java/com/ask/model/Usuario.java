package com.ask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "apellido_usuario", nullable = false, length = 50)
    private String apellidoUsuario;

    @Column(name = "dni_usuario", nullable = false, length = 8, unique = true)
    private String dniUsuario;

    @Column(name = "telefono_usuario", nullable = false, length = 9)
    private String telefonoUsuario;

    @Column(name = "correo_usuario", nullable = false, length = 150, unique = true)
    private String correoUsuario;

    @Column(name = "username_usuario", nullable = false, length = 150, unique = true)
    private String usernameUsuario;

    @Column(name = "contrasenia_usuario", nullable = false, length = 150)
    private String contraseniaUsuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rol;
}