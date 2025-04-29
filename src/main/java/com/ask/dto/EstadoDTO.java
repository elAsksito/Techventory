package com.ask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EstadoDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
}