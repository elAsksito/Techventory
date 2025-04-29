package com.ask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "id_rol")
    private UUID idRol;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(name = "descripcion_rol")
    private String descripcionRol;
}