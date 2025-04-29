package com.ask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoriaDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
}